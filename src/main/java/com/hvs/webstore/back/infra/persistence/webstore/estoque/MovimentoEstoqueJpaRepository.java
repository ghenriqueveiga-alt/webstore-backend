package com.hvs.webstore.back.infra.persistence.webstore.estoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MovimentoEstoqueJpaRepository extends JpaRepository<MovimentoEstoqueEntity, Long>, JpaSpecificationExecutor<MovimentoEstoqueEntity> {

    Optional<MovimentoEstoqueEntity> findByUuid(String uuid);
}
