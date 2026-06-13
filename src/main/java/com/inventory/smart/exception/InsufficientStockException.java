package com.inventory.smart.exception;

/**
 * Excepción lanzada cuando se intenta realizar una operación con stock insuficiente.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public class InsufficientStockException extends RuntimeException {
    
    /**
     * Crea una nueva excepción con el mensaje especificado.
     *
     * @param message el detalle del error
     */
    public InsufficientStockException(String message) {
        super(message);
    }
}
