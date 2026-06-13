package com.inventory.smart.repository;

import com.inventory.smart.model.MovimientoInventario;
import java.util.List;

/**
 * Interfaz para el repositorio de movimientos de inventario.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public interface MovimientoRepository extends IGenericRepository<MovimientoInventario, Long> {

    /**
     * Recupera el historial de movimientos de un producto.
     *
     * @param productoId el identificador del producto
     * @return lista de movimientos del producto ordenados por fecha
     */
    List<MovimientoInventario> findByProductoId(Long productoId);
}
