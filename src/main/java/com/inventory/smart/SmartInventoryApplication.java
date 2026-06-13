package com.inventory.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicializa el microservicio de Gestión de Inventario Inteligente.
 *
 * <p>Esta aplicación Spring Boot expone endpoints REST para administrar productos,
 * categorías, movimientos de stock y alertas.</p>
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@SpringBootApplication
public class SmartInventoryApplication {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comandos pasados al iniciar
     */
    public static void main(String[] args) {
        SpringApplication.run(SmartInventoryApplication.class, args);
    }
}
