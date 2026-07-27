package com.hvs.webstore.back.infra.persistence.television.programa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProgramaJpaRepository extends JpaRepository<ProgramaEntity, Long>, JpaSpecificationExecutor<ProgramaEntity> {

    Optional<ProgramaEntity> findByUuid(String uuid);
}
