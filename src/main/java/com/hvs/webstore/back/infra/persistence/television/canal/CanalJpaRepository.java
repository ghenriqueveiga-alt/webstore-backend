package com.hvs.webstore.back.infra.persistence.television.canal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CanalJpaRepository extends JpaRepository<CanalEntity, Long>, JpaSpecificationExecutor<CanalEntity> {

    Optional<CanalEntity> findByUuid(String uuid);
}
