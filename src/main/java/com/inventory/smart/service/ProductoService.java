package com.inventory.smart.service;

import com.inventory.smart.dto.ProductoRequest;
import com.inventory.smart.dto.ProductoResponse;

import java.util.List;

/**
 * Servicio encargado de la gestión de productos.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public interface ProductoService {

    /**
     * Lista productos con filtros opcionales.
     *
     * @param categoriaId ID de categoría opcional
     * @param precioMin precio mínimo opcional
     * @param precioMax precio máximo opcional
     * @param enStock filtrar solo los que tienen stock > 0
     * @return lista de productos
     */
    List<ProductoResponse> listar(Long categoriaId, Double precioMin, Double precioMax, Boolean enStock);

    /**
     * Busca un producto por ID.
     *
     * @param id identificador
     * @return producto encontrado
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    ProductoResponse findById(Long id);

    /**
     * Crea un producto.
     *
     * @param request datos del producto
     * @return producto creado
     */
    ProductoResponse crear(ProductoRequest );

    /**
     * Actualiza un producto existente.
     *
     * @param id identificador
     * @param request nuevos datos
     * @return producto actualizado
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    ProductoResponse actualizar(Long id, ProductoRequest request);

    /**
     * Elimina un producto.
     *
     * @param id identificador
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    void eliminar(Long id);

    /**
     * Busca productos por nombre.
     *
     * @param q texto a buscar
     * @return lista de productos coincidentes
     */
    List<ProductoResponse> buscarPorNombre(String q);

    /**
     * Lista productos ordenados.
     *
     * @param campo campo por el cual ordenar (nombre, precio, stock)
     * @param orden asc o desc
     * @return lista ordenada
     */
    List<ProductoResponse> listarOrdenados(String campo, String orden);
}
