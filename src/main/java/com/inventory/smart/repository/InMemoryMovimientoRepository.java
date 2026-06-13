package com.inventory.smart.repository;

import com.inventory.smart.model.MovimientoInventario;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación en memoria del repositorio de movimientos de inventario.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@Repository
public class InMemoryMovimientoRepository extends GenericInMemoryRepository<MovimientoInventario, Long> implements MovimientoRepository {

    @Override
    public List<MovimientoInventario> findByProductoId(Long productoId) {
        return dataStore.values().stream()
                .filter(m -> m.getProductoId().equals(productoId))
                .sorted(Comparator.comparing(MovimientoInventario::getFecha).reversed())
                .collect(Collectors.toList());
    }
}
