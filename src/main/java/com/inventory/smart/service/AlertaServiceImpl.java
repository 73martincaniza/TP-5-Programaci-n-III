package com.inventory.smart.service;

import com.inventory.smart.dto.AlertaStockResponse;
import com.inventory.smart.dto.CategoriaResponse;
import com.inventory.smart.dto.ProductoResponse;
import com.inventory.smart.model.NivelAlerta;
import com.inventory.smart.model.Producto;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de alertas que utiliza el patrón Strategy.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@Service
public class AlertaServiceImpl implements AlertaService {

    private final ProductoRepository productoRepository;
    private final AlertaStrategy alertaStrategy;

    /**
     * Constructor para inyección de dependencias.
     *
     * @param productoRepository repositorio de productos
     * @param alertaStrategy estrategia para evaluar niveles de alerta
     */
    public AlertaServiceImpl(ProductoRepository productoRepository, AlertaStrategy alertaStrategy) {
        this.productoRepository = productoRepository;
        this.alertaStrategy = alertaStrategy;
    }

    @Override
    public List<AlertaStockResponse> obtenerAlertasStockBajo() {
        return productoRepository.findAll().stream()
                .filter(p -> alertaStrategy.evaluar(p) != NivelAlerta.NORMAL)
                .map(p -> new AlertaStockResponse(mapToProductoResponse(p), alertaStrategy.evaluar(p)))
                .collect(Collectors.toList());
    }

    private ProductoResponse mapToProductoResponse(Producto p) {
        CategoriaResponse catRes = new CategoriaResponse(p.getCategoria().getId(), p.getCategoria().getNombre(), p.getCategoria().getDescripcion());
        return new ProductoResponse(p.getId(), p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getStock(), catRes);
    }
}
