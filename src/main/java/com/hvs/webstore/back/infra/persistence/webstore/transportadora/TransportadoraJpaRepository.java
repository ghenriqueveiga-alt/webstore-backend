package com.hvs.webstore.back.infra.persistence.webstore.transportadora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TransportadoraJpaRepository extends JpaRepository<TransportadoraEntity, Long>, JpaSpecificationExecutor<TransportadoraEntity> {

    Optional<TransportadoraEntity> findByUuid(String uuid);
}
