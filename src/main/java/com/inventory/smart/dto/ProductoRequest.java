package com.inventory.smart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * Record DTO para la creación y actualización de productos.
 *
 * @param nombre nombre del producto
 * @param descripcion descripción del producto
 * @param precio precio del producto
 * @param stockInicial stock inicial (sólo aplica en creación)
 * @param categoriaId ID de la categoría a la que pertenece
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public record ProductoRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
        String nombre,

        @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
        String descripcion,

        @PositiveOrZero(message = "El precio debe ser mayor o igual a 0")
        double precio,

        @PositiveOrZero(message = "El stock inicial debe ser mayor o igual a 0")
        int stockInicial,

        @NotNull(message = "La categoría es obligatoria")
        Long categoriaId
) {
}
