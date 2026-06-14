package com.inventory.smart.service;

import com.inventory.smart.model.Categoria;
import com.inventory.smart.model.Producto;
import com.inventory.smart.repository.CategoriaRepository;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio encargado de generar el reporte de performance empírica.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@Service
public class PerformanceReportService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param productoRepository repositorio de productos
     * @param categoriaRepository repositorio de categorías
     */
    public PerformanceReportService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Genera el reporte de performance midiendo los tiempos para 1k, 10k y 100k registros.
     *
     * @return un mapa estructurado con los resultados
     */
    public Map<String, Object> generarReporte() {
        Map<String, Object> reporte = new HashMap<>();
        
        Categoria catDummy = new Categoria(null, "Test Category", "Desc");
        catDummy = categoriaRepository.save(catDummy);

        reporte.put("1k", medirParaN(1000, catDummy));
        reporte.put("10k", medirParaN(10000, catDummy));
        reporte.put("100k", medirParaN(100000, catDummy));

        return reporte;
    }

    private Map<String, Long> medirParaN(int n, Categoria cat) {
        Map<String, Long> mediciones = new HashMap<>();

        // 1. Limpiar y Preparar
        productoRepository.findAll().forEach(p -> productoRepository.deleteById(p.getId()));
        
        // 2. Medir POST /api/productos (Tiempo promedio de inserción)
        long startInsert = System.nanoTime();
        for (int i = 0; i < n; i++) {
            productoRepository.save(new Producto(null, "Producto " + i, "Desc", 10.0, cat, 10));
        }
        long endInsert = System.nanoTime();
        mediciones.put("POST /api/productos (avg)", (endInsert - startInsert) / n);

        // Tomar un ID existente para pruebas
        List<Producto> actuales = productoRepository.findAll();
        Long randomId = actuales.get(n / 2).getId();

        // 3. Medir GET /api/productos
        long startGetAll = System.nanoTime();
        productoRepository.findAll();
        long endGetAll = System.nanoTime();
        mediciones.put("GET /api/productos", (endGetAll - startGetAll));

        // 4. Medir GET /api/productos/{id}
        long startGetId = System.nanoTime();
        productoRepository.findById(randomId);
        long endGetId = System.nanoTime();
        mediciones.put("GET /api/productos/{id}", (endGetId - startGetId));

        // 5. Medir GET /api/productos/buscar?q=
        long startBuscar = System.nanoTime();
        productoRepository.buscarPorNombre("Producto " + (n/2));
        long endBuscar = System.nanoTime();
        mediciones.put("GET /api/productos/buscar", (endBuscar - startBuscar));

        return mediciones;
    }
}}
