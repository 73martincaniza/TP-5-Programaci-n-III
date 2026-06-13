package com.inventory.smart.service;

import com.inventory.smart.model.NivelAlerta;
import com.inventory.smart.model.Producto;

/**
 * Patrón Strategy para evaluar el nivel de alerta de un producto en base a su stock.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public interface AlertaStrategy {

    /**
     * Evalúa el nivel de alerta de un producto.
     *
     * @param producto el producto a evaluar
     * @return el nivel de alerta correspondiente
     */
    NivelAlerta evaluar(Producto producto);
}
