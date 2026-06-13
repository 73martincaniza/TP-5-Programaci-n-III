package com.inventory.smart.model;

import java.time.LocalDateTime;

/**
 * Representa el registro de una entrada o salida de stock para un producto.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public class MovimientoInventario {
    private Long id;
    private Long productoId;
    private TipoMovimiento tipo;
    private int cantidad;
    private int stockResultante;
    private String motivo;
    private LocalDateTime fecha;

    /**
     * Crea un nuevo registro de movimiento de inventario.
     *
     * @param id identificador del movimiento
     * @param productoId identificador del producto asociado
     * @param tipo tipo de movimiento (ENTRADA o SALIDA)
     * @param cantidad cantidad involucrada en el movimiento
     * @param stockResultante cantidad final de stock luego del movimiento
     * @param motivo justificación o nota del movimiento
     * @param fecha marca de tiempo del movimiento
     */
    public MovimientoInventario(Long id, Long productoId, TipoMovimiento tipo, int cantidad, int stockResultante, String motivo, LocalDateTime fecha) {
        this.id = id;
        this.productoId = productoId;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.stockResultante = stockResultante;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    /**
     * Obtiene el ID del movimiento.
     *
     * @return ID del movimiento
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del movimiento.
     *
     * @param id ID a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del producto afectado.
     *
     * @return ID del producto
     */
    public Long getProductoId() {
        return productoId;
    }

    /**
     * Establece el ID del producto afectado.
     *
     * @param productoId ID del producto
     */
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    /**
     * Obtiene el tipo de movimiento.
     *
     * @return tipo de movimiento
     */
    public TipoMovimiento getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de movimiento.
     *
     * @param tipo nuevo tipo
     */
    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la cantidad de stock movida.
     *
     * @return cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de stock movida.
     *
     * @param cantidad cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el stock resultante tras el movimiento.
     *
     * @return stock resultante
     */
    public int getStockResultante() {
        return stockResultante;
    }

    /**
     * Establece el stock resultante tras el movimiento.
     *
     * @param stockResultante stock resultante
     */
    public void setStockResultante(int stockResultante) {
        this.stockResultante = stockResultante;
    }

    /**
     * Obtiene el motivo del movimiento.
     *
     * @return motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Establece el motivo del movimiento.
     *
     * @param motivo nuevo motivo
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Obtiene la fecha y hora del movimiento.
     *
     * @return fecha
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha y hora del movimiento.
     *
     * @param fecha fecha a establecer
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
