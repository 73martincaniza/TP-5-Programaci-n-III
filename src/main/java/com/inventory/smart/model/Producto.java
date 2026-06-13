package com.inventory.smart.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa un producto dentro del inventario.
 *
 * <p>El stock se maneja mediante {@link AtomicInteger} para asegurar la atomicidad
 * en entornos concurrentes y prevenir condiciones de carrera.</p>
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private Categoria categoria;
    private final AtomicInteger stock;

    /**
     * Crea un nuevo producto con su stock inicial.
     *
     * @param id identificador único del producto
     * @param nombre nombre del producto
     * @param descripcion descripción del producto
     * @param precio precio unitario
     * @param categoria categoría asociada al producto
     * @param stockInicial cantidad inicial en stock
     */
    public Producto(Long id, String nombre, String descripcion, double precio, Categoria categoria, int stockInicial) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = new AtomicInteger(stockInicial);
    }

    /**
     * Obtiene el identificador del producto.
     *
     * @return el identificador
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del producto.
     *
     * @param id el nuevo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     *
     * @return la descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     *
     * @param descripcion la nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return el precio unitario
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio el nuevo precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la categoría del producto.
     *
     * @return la categoría
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del producto.
     *
     * @param categoria la nueva categoría
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene la cantidad actual en stock de manera segura.
     *
     * @return la cantidad en stock
     */
    public int getStock() {
        return stock.get();
    }

    /**
     * Incrementa el stock atómicamente.
     *
     * @param cantidad cantidad a sumar
     * @return el nuevo stock resultante
     */
    public int incrementarStock(int cantidad) {
        return stock.addAndGet(cantidad);
    }

    /**
     * Decrementa el stock atómicamente.
     *
     * @param cantidad cantidad a restar
     * @return el nuevo stock resultante
     */
    public int decrementarStock(int cantidad) {
        return stock.addAndGet(-cantidad);
    }
}
