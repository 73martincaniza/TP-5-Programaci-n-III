package com.inventory.smart.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica para repositorios que define las operaciones CRUD básicas.
 *
 * @param <T>  tipo de la entidad gestionada
 * @param <ID> tipo del identificador de la entidad
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public interface IGenericRepository<T, ID> {

    /**
     * Recupera todas las entidades.
     *
     * @return lista de todas las entidades
     */
    List<T> findAll();

    /**
     * Busca una entidad por su identificador.
     *
     * @param id identificador a buscar
     * @return un Optional con la entidad si se encuentra, vacío en caso contrario
     */
    Optional<T> findById(ID id);

    /**
     * Guarda una entidad (crea o actualiza).
     *
     * @param entity la entidad a guardar
     * @return la entidad guardada con su ID asignado
     */
    T save(T entity);

    /**
     * Elimina una entidad por su identificador.
     *
     * @param id identificador de la entidad a eliminar
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    void deleteById(ID id);

    /**
     * Verifica si una entidad existe por su identificador.
     *
     * @param id identificador a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsById(ID id);

    /**
     * Cuenta el total de entidades.
     *
     * @return cantidad de entidades
     */
    long count();
}
