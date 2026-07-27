package com.hvs.webstore.back.infra.persistence.webstore.pix;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PixJpaRepository extends JpaRepository<PixEntity, Long>, JpaSpecificationExecutor<PixEntity> {

    Optional<PixEntity> findByUuid(String uuid);
}
