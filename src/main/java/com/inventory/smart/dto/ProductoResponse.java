package com.inventory.smart.dto;

/**
 * Record DTO para devolver los datos de un producto.
 *
 * @param id ID del producto
 * @param nombre nombre del producto
 * @param descripcion descripción
 * @param precio precio
 * @param stock cantidad actual en stock
 * @param categoria categoría anidada del producto
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public record ProductoResponse(
        Long id,
        String nombre,
        String descripcion,
        double precio,
        int stock,
        CategoriaResponse categoria
) {
}
