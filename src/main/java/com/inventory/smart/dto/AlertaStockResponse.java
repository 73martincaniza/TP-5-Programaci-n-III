package com.inventory.smart.dto;

import com.inventory.smart.model.NivelAlerta;

/**
 * Record DTO para representar un producto en alerta de stock.
 *
 * @param producto el producto afectado
 * @param nivel el nivel de alerta (BAJO o CRITICO)
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public record AlertaStockResponse(
        ProductoResponse producto,
        NivelAlerta nivel
) {
}
