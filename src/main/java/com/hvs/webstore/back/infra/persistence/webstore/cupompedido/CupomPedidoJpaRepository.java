package com.hvs.webstore.back.infra.persistence.webstore.cupompedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CupomPedidoJpaRepository extends JpaRepository<CupomPedidoEntity, Long>, JpaSpecificationExecutor<CupomPedidoEntity> {

    Optional<CupomPedidoEntity> findByUuid(String uuid);
    List<CupomPedidoEntity> findByPedido_Id(Long pedidoId);
    List<CupomPedidoEntity> findByCupom_Id(Long cupomId);
}
