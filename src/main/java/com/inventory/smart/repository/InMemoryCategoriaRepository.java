package com.inventory.smart.repository;

import com.inventory.smart.model.Categoria;
import org.springframework.stereotype.Repository;

/**
 * Implementación en memoria del repositorio de categorías.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@Repository
public class InMemoryCategoriaRepository extends GenericInMemoryRepository<Categoria, Long> implements CategoriaRepository {
}
