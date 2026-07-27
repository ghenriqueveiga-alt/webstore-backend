package com.hvs.webstore.back.infra.persistence.webstore.avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AvaliacaoJpaRepository extends JpaRepository<AvaliacaoEntity, Long>, JpaSpecificationExecutor<AvaliacaoEntity> {

    Optional<AvaliacaoEntity> findByUuid(String uuid);
    Page<AvaliacaoEntity> findByProdutoId(Long produtoId, Pageable pageable);
}
