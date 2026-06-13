package com.inventory.smart.service;

import com.inventory.smart.dto.AlertaStockResponse;
import java.util.List;

/**
 * Servicio encargado de la gestión de alertas de stock.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public interface AlertaService {

    /**
     * Obtiene una lista de productos que se encuentran con stock bajo o crítico.
     *
     * @return lista de alertas
     */
    List<AlertaStockResponse> obtenerAlertasStockBajo();
}
