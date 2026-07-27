package com.hvs.webstore.back.infra.persistence.webstore.notafiscal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface NotaFiscalJpaRepository extends JpaRepository<NotaFiscalEntity, Long>, JpaSpecificationExecutor<NotaFiscalEntity> {

    Optional<NotaFiscalEntity> findByUuid(String uuid);
    Optional<NotaFiscalEntity> findByChaveAcesso(String chaveAcesso);
    Optional<NotaFiscalEntity> findByPedidoId(Long pedidoId);
}
