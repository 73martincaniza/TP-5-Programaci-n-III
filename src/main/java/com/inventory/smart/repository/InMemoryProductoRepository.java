package com.inventory.smart.repository;

import com.inventory.smart.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación en memoria del repositorio de productos.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@Repository
public class InMemoryProductoRepository extends GenericInMemoryRepository<Producto, Long> implements ProductoRepository {

    @Override
    public List<Producto> findByCategoria(Long categoriaId) {
        return dataStore.values().stream()
                .filter(p -> p.getCategoria().getId().equals(categoriaId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> buscarPorNombre(String texto) {
        String lower = texto.toLowerCase();
        return dataStore.values().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }
}
