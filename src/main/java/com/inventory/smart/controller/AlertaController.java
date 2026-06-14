package com.inventory.smart.controller;

import com.inventory.smart.dto.AlertaStockResponse;
import com.inventory.smart.service.AlertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para las alertas de stock.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService alertaService;

    /**
     * Constructor para inyección.
     *
     * @param alertaService servicio de alertas
     */
    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    /**
     * Obtiene productos con stock bajo o crítico.
     *
     * @return lista de productos en alerta
     */
    @GetMapping("/stock-bajo")
    public ResponseEntity<List<AlertaStockResponse>> stockBajo() {
        return ResponseEntity.ok(alertaService.obtenerAlertasStockBajo());
    }

