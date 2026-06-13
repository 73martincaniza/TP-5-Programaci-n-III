package com.inventory.smart.service;

import com.inventory.smart.dto.CategoriaResponse;
import com.inventory.smart.dto.ProductoRequest;
import com.inventory.smart.dto.ProductoResponse;
import com.inventory.smart.exception.ResourceNotFoundException;
import com.inventory.smart.model.Categoria;
import com.inventory.smart.model.Producto;
import com.inventory.smart.repository.CategoriaRepository;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementación del servicio de productos.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    /**
     * Constructor para inyección de dependencias.
     *
     * @param productoRepository repositorio de productos
     * @param categoriaRepository repositorio de categorías
     */
    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<ProductoResponse> listar(Long categoriaId, Double precioMin, Double precioMax, Boolean enStock) {
        Stream<Producto> stream = productoRepository.findAll().stream();

        if (categoriaId != null) {
            stream = stream.filter(p -> p.getCategoria().getId().equals(categoriaId));
        }
        if (precioMin != null) {
            stream = stream.filter(p -> p.getPrecio() >= precioMin);
        }
        if (precioMax != null) {
            stream = stream.filter(p -> p.getPrecio() <= precioMax);
        }
        if (enStock != null && enStock) {
            stream = stream.filter(p -> p.getStock() > 0);
        }

        return stream.map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public ProductoResponse findById(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        return mapToResponse(p);
    }

    @Override
    public ProductoResponse crear(ProductoRequest request) {
        Categoria cat = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + request.categoriaId()));

        Producto nuevo = new Producto(null, request.nombre(), request.descripcion(), request.precio(), cat, request.stockInicial());
        Producto guardado = productoRepository.save(nuevo);
        return mapToResponse(guardado);
    }

    @Override
    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        
        Categoria cat = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + request.categoriaId()));

        existente.setNombre(request.nombre());
        existente.setDescripcion(request.descripcion());
        existente.setPrecio(request.precio());
        existente.setCategoria(cat);

        Producto actualizado = productoRepository.save(existente);
        return mapToResponse(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<ProductoResponse> buscarPorNombre(String q) {
        if (q == null || q.isBlank()) {
            throw new IllegalArgumentException("El término de búsqueda no puede estar vacío");
        }
        return productoRepository.buscarPorNombre(q).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoResponse> listarOrdenados(String campo, String orden) {
        List<Producto> productos = productoRepository.findAll();
        
        Comparator<Producto> comp;
        switch (campo.toLowerCase()) {
            case "precio":
                comp = Comparator.comparing(Producto::getPrecio);
                break;
            case "stock":
                comp = Comparator.comparing(Producto::getStock);
                break;
            case "nombre":
            default:
                comp = Comparator.comparing(Producto::getNombre);
                break;
        }

        if ("desc".equalsIgnoreCase(orden)) {
            comp = comp.reversed();
        }

        productos.sort(comp);
        return productos.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private ProductoResponse mapToResponse(Producto p) {
        CategoriaResponse catRes = new CategoriaResponse(p.getCategoria().getId(), p.getCategoria().getNombre(), p.getCategoria().getDescripcion());
        return new ProductoResponse(p.getId(), p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getStock(), catRes);
    }
}
