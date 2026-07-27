package com.hvs.webstore.back.infra.persistence.webstore.cupom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CupomJpaRepository extends JpaRepository<CupomEntity, Long>, JpaSpecificationExecutor<CupomEntity> {

    Optional<CupomEntity> findByUuid(String uuid);
    Optional<CupomEntity> findByCodigo(String codigo);
}
