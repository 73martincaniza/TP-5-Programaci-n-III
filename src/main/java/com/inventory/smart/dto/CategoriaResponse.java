package com.inventory.smart.dto;

/**
 * Record DTO para devolver los datos de una categoría.
 *
 * @param id ID de la categoría
 * @param nombre nombre de la categoría
 * @param descripcion descripción
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public record CategoriaResponse(
        Long id,
        String nombre,
        String descripcion
) {
}
