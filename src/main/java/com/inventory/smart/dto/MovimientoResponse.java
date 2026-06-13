package com.inventory.smart.dto;

import com.inventory.smart.model.TipoMovimiento;
import java.time.LocalDateTime;

/**
 * Record DTO para devolver los datos de un movimiento.
 *
 * @param id ID del movimiento
 * @param productoId ID del producto afectado
 * @param tipo ENTRADA o SALIDA
 * @param cantidad cantidad movida
 * @param stockResultante cómo quedó el stock tras la operación
 * @param motivo razón del movimiento
 * @param fecha cuándo ocurrió
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public record MovimientoResponse(
        Long id,
        Long productoId,
        TipoMovimiento tipo,
        int cantidad,
        int stockResultante,
        String motivo,
        LocalDateTime fecha
) {
}
