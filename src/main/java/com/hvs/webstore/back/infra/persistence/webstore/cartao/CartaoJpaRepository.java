package com.hvs.webstore.back.infra.persistence.webstore.cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CartaoJpaRepository extends JpaRepository<CartaoEntity, Long>, JpaSpecificationExecutor<CartaoEntity> {

    Optional<CartaoEntity> findByUuid(String uuid);
}
