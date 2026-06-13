package com.inventory.smart.controller;

import com.inventory.smart.dto.ProductoRequest;
import com.inventory.smart.dto.ProductoResponse;
import com.inventory.smart.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para la gestión de productos.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Constructor para inyección.
     *
     * @param productoService servicio de productos
     */
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Lista productos con filtros opcionales.
     *
     * @param categoria ID de categoría
     * @param precioMin precio mínimo
     * @param precioMax precio máximo
     * @param enStock bandera de stock
     * @return lista filtrada
     */
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listar(
            @RequestParam(required = false) Long categoria,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false) Boolean enStock) {
        return ResponseEntity.ok(productoService.listar(categoria, precioMin, precioMax, enStock));
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id identificador
     * @return producto encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    /**
     * Crea un nuevo producto.
     *
     * @param request datos del producto
     * @return producto creado y Location header
     */
    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request) {
        ProductoResponse creado = productoService.crear(request);
        return ResponseEntity.created(URI.create("/api/productos/" + creado.id())).body(creado);
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id identificador
     * @param request datos actualizados
     * @return producto actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoService.actualizar(id, request));
    }

    /**
     * Elimina un producto.
     *
     * @param id identificador
     * @return 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Búsqueda de productos por nombre.
     *
     * @param q texto a buscar
     * @return lista de resultados
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponse>> buscar(@RequestParam String q) {
        return ResponseEntity.ok(productoService.buscarPorNombre(q));
    }

    /**
     * Lista de productos ordenados.
     *
     * @param campo nombre, precio o stock
     * @param orden asc o desc
     * @return lista ordenada
     */
    @GetMapping("/ordenados")
    public ResponseEntity<List<ProductoResponse>> ordenados(
            @RequestParam(defaultValue = "nombre") String campo,
            @RequestParam(defaultValue = "asc") String orden) {
        return ResponseEntity.ok(productoService.listarOrdenados(campo, orden));
    }
}
