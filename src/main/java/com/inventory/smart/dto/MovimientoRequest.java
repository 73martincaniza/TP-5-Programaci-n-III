package com.inventory.smart.dto;

import com.inventory.smart.model.TipoMovimiento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Record DTO para registrar un movimiento de inventario.
 *
 * @param productoId el producto involucrado
 * @param tipo ENTRADA o SALIDA
 * @param cantidad cuánto stock se mueve
 * @param motivo por qué se realiza el movimiento
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public record MovimientoRequest(
        @NotNull(message = "El ID del producto es obligatorio")
        Long productoId,

        @NotNull(message = "El tipo de movimiento es obligatorio (ENTRADA o SALIDA)")
        TipoMovimiento tipo,

        @Min(value = 1, message = "La cantidad debe ser mayor a 0")
        int cantidad,

        @NotBlank(message = "El motivo es obligatorio")
        String motivo
) {
}
