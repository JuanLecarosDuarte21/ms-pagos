
package com.tiendapc.pagos.service;

import com.tiendapc.pagos.model.Pago;
import com.tiendapc.pagos.repository.PagoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    // Crear un pago
    public Pago crearPago(Pago pago) {
        pago.setId(null);
        pago.setEstado("PENDIENTE");
        pago.setFechaPago(null);
        return pagoRepository.save(pago);
    }

    // Listar todos los pagos
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    // Buscar pago por ID
    public Optional<Pago> buscarPorId(Long id) {
        return pagoRepository.findById(id);
    }

    // Buscar pagos por pedido
    public List<Pago> buscarPorPedido(Long pedidoId) {
        return pagoRepository.findByPedidoId(pedidoId);
    }

    // Buscar pagos por estado
    public List<Pago> buscarPorEstado(String estado) {
        return pagoRepository.findByEstado(
            estado.trim().toUpperCase()
        );
    }

    // Buscar pagos por método de pago
    public List<Pago> buscarPorMetodo(String metodoPago) {
        return pagoRepository.findByMetodoPago(
            metodoPago.trim().toUpperCase()
        );
    }

    // Buscar pagos por pedido y estado
    public List<Pago> buscarPorPedidoYEstado(
            Long pedidoId, String estado) {

        return pagoRepository.findByPedidoIdAndEstado(
            pedidoId,
            estado.trim().toUpperCase()
        );
    }

    // Actualizar estado de un pago
    public Optional<Pago> actualizarEstado(
            Long id, String estado) {

        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException(
                "El estado no puede estar vacío"
            );
        }

        return pagoRepository.findById(id).map(pago -> {
            pago.setEstado(estado.trim().toUpperCase());
            return pagoRepository.save(pago);
        });
    }

    // Eliminar un pago
    public boolean eliminarPago(Long id) {

        if (!pagoRepository.existsById(id)) {
            return false;
        }

        pagoRepository.deleteById(id);
        return true;
    }
}
