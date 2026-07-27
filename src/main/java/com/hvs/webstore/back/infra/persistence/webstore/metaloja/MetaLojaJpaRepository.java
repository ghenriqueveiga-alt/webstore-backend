package com.hvs.webstore.back.infra.persistence.webstore.metaloja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MetaLojaJpaRepository extends JpaRepository<MetaLojaEntity, Long>, JpaSpecificationExecutor<MetaLojaEntity> {

    Optional<MetaLojaEntity> findByUuid(String uuid);
    Optional<MetaLojaEntity> findByChave(String chave);
}
