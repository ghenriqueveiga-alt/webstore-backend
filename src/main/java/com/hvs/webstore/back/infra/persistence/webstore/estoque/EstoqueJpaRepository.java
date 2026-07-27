package com.hvs.webstore.back.infra.persistence.webstore.estoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EstoqueJpaRepository extends JpaRepository<EstoqueEntity, Long>, JpaSpecificationExecutor<EstoqueEntity> {

    Optional<EstoqueEntity> findByUuid(String uuid);
    Optional<EstoqueEntity> findByProdutoId(Long produtoId);
}
