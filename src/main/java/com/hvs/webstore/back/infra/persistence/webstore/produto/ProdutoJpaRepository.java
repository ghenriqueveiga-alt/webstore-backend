package com.hvs.webstore.back.infra.persistence.webstore.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProdutoJpaRepository extends JpaRepository<ProdutoEntity, Long>, JpaSpecificationExecutor<ProdutoEntity> {

    Optional<ProdutoEntity> findByUuid(String uuid);
}
