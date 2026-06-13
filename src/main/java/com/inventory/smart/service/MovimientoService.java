package com.inventory.smart.service;

import com.inventory.smart.dto.MovimientoRequest;
import com.inventory.smart.dto.MovimientoResponse;

import java.util.List;

/**
 * Servicio encargado de la gestión de movimientos de inventario.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public interface MovimientoService {

    /**
     * Registra un nuevo movimiento de inventario (ENTRADA o SALIDA).
     *
     * @param request datos del movimiento
     * @return el movimiento registrado
     * @throws com.inventory.smart.exception.ResourceNotFoundException si el producto no existe
     * @throws com.inventory.smart.exception.InsufficientStockException si no hay stock suficiente para una salida
     */
    MovimientoResponse registrarMovimiento(MovimientoRequest request);

    /**
     * Obtiene el historial de movimientos de un producto.
     *
     * @param productoId identificador del producto
     * @return lista de movimientos
     */
    List<MovimientoResponse> obtenerHistorialPorProducto(Long productoId);
}
