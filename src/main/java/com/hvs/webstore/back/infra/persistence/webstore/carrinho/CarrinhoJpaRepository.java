package com.hvs.webstore.back.infra.persistence.webstore.carrinho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CarrinhoJpaRepository extends JpaRepository<CarrinhoEntity, Long>, JpaSpecificationExecutor<CarrinhoEntity> {

    Optional<CarrinhoEntity> findByUuid(String uuid);
    Optional<CarrinhoEntity> findByUsuario_Id(Long usuarioId);
}
