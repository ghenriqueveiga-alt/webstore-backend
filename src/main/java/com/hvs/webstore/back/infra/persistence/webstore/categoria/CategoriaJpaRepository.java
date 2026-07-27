package com.hvs.webstore.back.infra.persistence.webstore.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity, Long>, JpaSpecificationExecutor<CategoriaEntity> {

    Optional<CategoriaEntity> findByUuid(String uuid);
}
