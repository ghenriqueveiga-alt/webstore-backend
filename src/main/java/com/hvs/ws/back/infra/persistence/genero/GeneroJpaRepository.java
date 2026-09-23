package com.hvs.ws.back.infra.persistence.genero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface GeneroJpaRepository extends JpaRepository<GeneroEntity, Long>, JpaSpecificationExecutor<GeneroEntity> {

    Optional<GeneroEntity> findByUuid(String uuid);
}
