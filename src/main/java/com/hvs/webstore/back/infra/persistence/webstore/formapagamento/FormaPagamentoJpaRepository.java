package com.hvs.webstore.back.infra.persistence.webstore.formapagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FormaPagamentoJpaRepository extends JpaRepository<FormaPagamentoEntity, Long>, JpaSpecificationExecutor<FormaPagamentoEntity> {

    Optional<FormaPagamentoEntity> findByUuid(String uuid);
}
