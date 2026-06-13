package com.inventory.smart.service;

import com.inventory.smart.dto.MovimientoRequest;
import com.inventory.smart.dto.MovimientoResponse;
import com.inventory.smart.exception.InsufficientStockException;
import com.inventory.smart.exception.ResourceNotFoundException;
import com.inventory.smart.model.MovimientoInventario;
import com.inventory.smart.model.Producto;
import com.inventory.smart.model.TipoMovimiento;
import com.inventory.smart.repository.MovimientoRepository;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de movimientos de inventario.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;

    /**
     * Constructor para inyección de dependencias.
     *
     * @param movimientoRepository repositorio de movimientos
     * @param productoRepository repositorio de productos
     */
    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, ProductoRepository productoRepository) {
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public MovimientoResponse registrarMovimiento(MovimientoRequest request) {
        Producto producto = productoRepository.findById(request.productoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + request.productoId()));

        int nuevoStock;
        if (request.tipo() == TipoMovimiento.ENTRADA) {
            nuevoStock = producto.incrementarStock(request.cantidad());
        } else {
            // Es una salida. Chequeamos si es posible (aunque incrementar negativamente no falla per se, no queremos stock negativo)
            int stockActual = producto.getStock();
            if (stockActual < request.cantidad()) {
                throw new InsufficientStockException("No se pueden retirar " + request.cantidad() + " unidades. Stock disponible: " + stockActual);
            }
            // Decremento atómico
            nuevoStock = producto.decrementarStock(request.cantidad());
            
            // Verificación post-decremento en caso de concurrencia extrema (si otro hilo retiró antes)
            if (nuevoStock < 0) {
                producto.incrementarStock(request.cantidad()); // rollback
                throw new InsufficientStockException("Stock insuficiente debido a concurrencia. Intente nuevamente.");
            }
        }

        MovimientoInventario mov = new MovimientoInventario(
                null, 
                producto.getId(), 
                request.tipo(), 
                request.cantidad(), 
                nuevoStock, 
                request.motivo(), 
                LocalDateTime.now()
        );

        MovimientoInventario guardado = movimientoRepository.save(mov);
        return mapToResponse(guardado);
    }

    @Override
    public List<MovimientoResponse> obtenerHistorialPorProducto(Long productoId) {
        return movimientoRepository.findByProductoId(productoId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private MovimientoResponse mapToResponse(MovimientoInventario mov) {
        return new MovimientoResponse(
                mov.getId(),
                mov.getProductoId(),
                mov.getTipo(),
                mov.getCantidad(),
                mov.getStockResultante(),
                mov.getMotivo(),
                mov.getFecha()
        );
    }
}
