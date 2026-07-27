package com.hvs.webstore.back.infra.persistence.television.corte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CorteJpaRepository extends JpaRepository<CorteEntity, Long>, JpaSpecificationExecutor<CorteEntity> {

    Optional<CorteEntity> findByUuid(String uuid);
}
