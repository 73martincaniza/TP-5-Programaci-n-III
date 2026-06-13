package com.inventory.smart.controller;

import com.inventory.smart.dto.MovimientoRequest;
import com.inventory.smart.dto.MovimientoResponse;
import com.inventory.smart.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para el registro de movimientos.
 *
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    /**
     * Constructor para inyección.
     *
     * @param movimientoService servicio de movimientos
     */
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    /**
     * Registra un movimiento.
     *
     * @param request datos del movimiento
     * @return movimiento registrado
     */
    @PostMapping
    public ResponseEntity<MovimientoResponse> registrarMovimiento(@Valid @RequestBody MovimientoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.registrarMovimiento(request));
    }

    /**
     * Obtiene el historial de un producto.
     *
     * @param id identificador del producto
     * @return historial de movimientos
     */
    @GetMapping("/producto/{id}")
    public ResponseEntity<List<MovimientoResponse>> historialPorProducto(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.obtenerHistorialPorProducto(id));
    }
}
