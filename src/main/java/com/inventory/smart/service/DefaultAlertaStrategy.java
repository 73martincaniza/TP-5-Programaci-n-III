package com.inventory.smart.service;

import com.inventory.smart.config.StockConfig;
import com.inventory.smart.model.NivelAlerta;
import com.inventory.smart.model.Producto;
import org.springframework.stereotype.Component;

/**
 * Implementación por defecto de la estrategia de alertas usando los valores configurados en application.yml.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@Component
public class DefaultAlertaStrategy implements AlertaStrategy {

    private final StockConfig stockConfig;

    /**
     * Crea la estrategia con la configuración de stock.
     *
     * @param stockConfig la configuración leída del properties
     */
    public DefaultAlertaStrategy(StockConfig stockConfig) {
        this.stockConfig = stockConfig;
    }

    @Override
    public NivelAlerta evaluar(Producto producto) {
        int stockActual = producto.getStock();
        if (stockActual < stockConfig.getCritico()) {
            return NivelAlerta.CRITICO;
        } else if (stockActual < stockConfig.getMinimo()) {
            return NivelAlerta.BAJO;
        }
        return NivelAlerta.NORMAL;
    }
}
