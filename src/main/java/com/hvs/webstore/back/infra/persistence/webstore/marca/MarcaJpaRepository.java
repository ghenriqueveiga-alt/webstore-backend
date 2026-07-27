package com.hvs.webstore.back.infra.persistence.webstore.marca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MarcaJpaRepository extends JpaRepository<MarcaEntity, Long>, JpaSpecificationExecutor<MarcaEntity> {

    Optional<MarcaEntity> findByUuid(String uuid);
}
