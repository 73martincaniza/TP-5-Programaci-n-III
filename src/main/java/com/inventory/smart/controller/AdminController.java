package com.inventory.smart.controller;

import com.inventory.smart.service.PerformanceReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controlador REST administrativo para la generación de reportes de rendimiento.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final PerformanceReportService performanceReportService;

    /**
     * Constructor para inyección.
     *
     * @param performanceReportService servicio de reporte
     */
    public AdminController(PerformanceReportService performanceReportService) {
        this.performanceReportService = performanceReportService;
    }

    /**
     * Ejecuta y obtiene el reporte de performance en tiempo real.
     *
     * @return mapa JSON con tiempos medidos en nanosegundos
     */
    @GetMapping("/performance-report")
    public ResponseEntity<Map<String, Object>> performanceReport() {
        return ResponseEntity.ok(performanceReportService.generarReporte());
    }
}
