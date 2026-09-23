package com.hvs.ws.back.infra.persistence.bloco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BlocoJpaRepository extends JpaRepository<BlocoEntity, Long>, JpaSpecificationExecutor<BlocoEntity> {

    Optional<BlocoEntity> findByUuid(String uuid);
}
