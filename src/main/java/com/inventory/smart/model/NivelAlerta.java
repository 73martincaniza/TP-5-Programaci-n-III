package com.inventory.smart.model;

/**
 * Define los niveles de alerta según el stock de un producto.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public enum NivelAlerta {
    /**
     * El stock está por encima del umbral mínimo.
     */
    NORMAL,
    
    /**
     * El stock está por debajo del umbral mínimo pero por encima del crítico.
     */
    BAJO,
    
    /**
     * El stock está por debajo del umbral crítico.
     */
    CRITICO
}
