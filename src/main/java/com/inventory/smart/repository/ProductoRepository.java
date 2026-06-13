package com.inventory.smart.repository;

import com.inventory.smart.model.Producto;
import java.util.List;

/**
 * Interfaz para el repositorio de productos.
 * Define consultas específicas para productos además del CRUD básico.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public interface ProductoRepository extends IGenericRepository<Producto, Long> {

    /**
     * Busca productos pertenecientes a una categoría específica.
     *
     * @param categoriaId identificador de la categoría
     * @return lista de productos
     */
    List<Producto> findByCategoria(Long categoriaId);

    /**
     * Busca productos cuyo nombre contenga el texto especificado, ignorando mayúsculas y minúsculas.
     *
     * @param texto texto a buscar
     * @return lista de productos coincidentes
     */
    List<Producto> buscarPorNombre(String texto);
}
