package com.inventory.smart.service;

import com.inventory.smart.dto.CategoriaRequest;
import com.inventory.smart.dto.CategoriaResponse;
import java.util.List;

/**
 * Servicio encargado de la gestión de categorías.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public interface CategoriaService {

    /**
     * Lista todas las categorías.
     *
     * @return lista de respuestas de categoría
     */
    List<CategoriaResponse> findAll();

    /**
     * Busca una categoría por su ID.
     *
     * @param id identificador de la categoría
     * @return la categoría encontrada
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    CategoriaResponse findById(Long id);

    /**
     * Crea una nueva categoría.
     *
     * @param request datos de la categoría
     * @return la categoría creada
     */
    CategoriaResponse crear(CategoriaRequest request);

    /**
     * Actualiza una categoría existente.
     *
     * @param id identificador de la categoría a actualizar
     * @param request nuevos datos
     * @return la categoría actualizada
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    CategoriaResponse actualizar(Long id, CategoriaRequest request);

    /**
     * Elimina una categoría si no tiene productos asociados.
     *
     * @param id identificador de la categoría
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     * @throws com.inventory.smart.exception.BusinessRuleException si tiene productos asociados
     */
    void eliminar(Long id);
}
