package com.hvs.webstore.back.infra.persistence.webstore.variacaoproduto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.List;

public interface VariacaoProdutoJpaRepository extends JpaRepository<VariacaoProdutoEntity, Long>, JpaSpecificationExecutor<VariacaoProdutoEntity> {

    Optional<VariacaoProdutoEntity> findByUuid(String uuid);
    List<VariacaoProdutoEntity> findByProduto_Id(Long produtoId);
}
