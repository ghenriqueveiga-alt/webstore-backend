package com.hvs.webstore.back.infra.persistence.webstore.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PagamentoJpaRepository extends JpaRepository<PagamentoEntity, Long>, JpaSpecificationExecutor<PagamentoEntity> {

    Optional<PagamentoEntity> findByUuid(String uuid);
    Optional<PagamentoEntity> findByPedidoId(Long pedidoId);
}
