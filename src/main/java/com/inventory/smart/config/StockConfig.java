package com.inventory.smart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Propiedades de configuración para los umbrales de stock.
 * Se leen desde application.yml bajo el prefijo "inventario.stock".
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "inventario.stock")
public class StockConfig {

    private int minimo = 10;
    private int critico = 3;

    /**
     * Obtiene el umbral mínimo.
     *
     * @return el umbral mínimo
     */
    public int getMinimo() {
        return minimo;
    }

    /**
     * Establece el umbral mínimo.
     *
     * @param minimo el nuevo umbral mínimo
     */
    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    /**
     * Obtiene el umbral crítico.
     *
     * @return el umbral crítico
     */
    public int getCritico() {
        return critico;
    }

    /**
     * Establece el umbral crítico.
     *
     * @param critico el nuevo umbral crítico
     */
    public void setCritico(int critico) {
        this.critico = critico;
    }
}
