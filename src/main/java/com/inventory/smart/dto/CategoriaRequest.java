package com.inventory.smart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Record DTO para la creación y actualización de categorías.
 *
 * @param nombre nombre de la categoría
 * @param descripcion descripción de la categoría
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public record CategoriaRequest(
        @NotBlank(message = "El nombre de la categoría es obligatorio")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
        String nombre,

        @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
        String descripcion
) {
}
