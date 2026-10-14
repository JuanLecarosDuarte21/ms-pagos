
package com.tiendapc.pagos.controller;

import com.tiendapc.pagos.model.Pago;
import com.tiendapc.pagos.service.PagoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    // Crear un pago
    @PostMapping
    public ResponseEntity<Pago> crearPago(
            @Valid @RequestBody Pago pago) {

        Pago nuevoPago = pagoService.crearPago(pago);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoPago);
    }

    // Listar todos los pagos
    @GetMapping
    public List<Pago> listarPagos() {
        return pagoService.listarPagos();
    }

    // Buscar pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscarPorId(
            @PathVariable Long id) {

        return pagoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar pagos por pedido
    @GetMapping("/pedido/{pedidoId}")
    public List<Pago> buscarPorPedido(
            @PathVariable Long pedidoId) {

        return pagoService.buscarPorPedido(pedidoId);
    }

    // Buscar pagos por estado
    @GetMapping("/estado/{estado}")
    public List<Pago> buscarPorEstado(
            @PathVariable String estado) {

        return pagoService.buscarPorEstado(estado);
    }

    // Buscar pagos por método de pago
    @GetMapping("/metodo/{metodoPago}")
    public List<Pago> buscarPorMetodo(
            @PathVariable String metodoPago) {

        return pagoService.buscarPorMetodo(metodoPago);
    }

    // Buscar pagos por pedido y estado
    @GetMapping("/pedido/{pedidoId}/estado/{estado}")
    public List<Pago> buscarPorPedidoYEstado(
            @PathVariable Long pedidoId,
            @PathVariable String estado) {

        return pagoService.buscarPorPedidoYEstado(
                pedidoId, estado);
    }

    // Actualizar el estado de un pago
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(
            @PathVariable Long id,
            @RequestBody ActualizarEstadoRequest request) {

        try {
            return pagoService
                    .actualizarEstado(id, request.estado())
                    .<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() ->
                            ResponseEntity.notFound().build());

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // Eliminar un pago
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(
            @PathVariable Long id) {

        if (pagoService.eliminarPago(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    public record ActualizarEstadoRequest(String estado) {
    }
}
