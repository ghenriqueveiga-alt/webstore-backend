package com.hvs.webstore.back.infra.persistence.webstore.frete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FreteJpaRepository extends JpaRepository<FreteEntity, Long>, JpaSpecificationExecutor<FreteEntity> {

    Optional<FreteEntity> findByUuid(String uuid);
}
