package com.hvs.webstore.back.infra.persistence.webstore.permissao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PermissaoJpaRepository extends JpaRepository<PermissaoEntity, Long>, JpaSpecificationExecutor<PermissaoEntity> {

    Optional<PermissaoEntity> findByUuid(String uuid);
    Optional<PermissaoEntity> findByChave(String chave);
}
