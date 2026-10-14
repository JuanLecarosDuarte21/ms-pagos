
package com.tiendapc.pagos.repository;

import com.tiendapc.pagos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByPedidoId(Long pedidoId);

    List<Pago> findByEstado(String estado);

    List<Pago> findByMetodoPago(String metodoPago);

    List<Pago> findByPedidoIdAndEstado(Long pedidoId, String estado);
}
