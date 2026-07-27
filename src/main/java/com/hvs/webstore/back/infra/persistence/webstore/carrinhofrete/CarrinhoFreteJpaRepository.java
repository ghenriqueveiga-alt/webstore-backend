package com.hvs.webstore.back.infra.persistence.webstore.carrinhofrete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CarrinhoFreteJpaRepository extends JpaRepository<CarrinhoFreteEntity, Long>, JpaSpecificationExecutor<CarrinhoFreteEntity> {

    Optional<CarrinhoFreteEntity> findByUuid(String uuid);
    List<CarrinhoFreteEntity> findByCarrinho_Id(Long carrinhoId);
}
