package com.hvs.webstore.back.infra.persistence.webstore.imagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ImagemJpaRepository extends JpaRepository<ImagemEntity, Long>, JpaSpecificationExecutor<ImagemEntity> {

    Optional<ImagemEntity> findByUuid(String uuid);
}
