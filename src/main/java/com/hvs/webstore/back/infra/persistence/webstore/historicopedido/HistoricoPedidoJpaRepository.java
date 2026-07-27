package com.hvs.webstore.back.infra.persistence.webstore.historicopedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface HistoricoPedidoJpaRepository extends JpaRepository<HistoricoPedidoEntity, Long>, JpaSpecificationExecutor<HistoricoPedidoEntity> {

    Optional<HistoricoPedidoEntity> findByUuid(String uuid);
    List<HistoricoPedidoEntity> findByPedidoIdOrderByDataCriacaoDesc(Long pedidoId);
}
