package com.hvs.ws.back.infra.persistence.arquivo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ArquivoJpaRepository extends JpaRepository<ArquivoEntity, Long>, JpaSpecificationExecutor<ArquivoEntity> {

    Optional<ArquivoEntity> findByUuid(String uuid);
}
