package com.hvs.webstore.back.infra.persistence.webstore.video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface VideoJpaRepository extends JpaRepository<VideoEntity, Long>, JpaSpecificationExecutor<VideoEntity> {

    Optional<VideoEntity> findByUuid(String uuid);
}
