package com.hvs.webstore.back.infra.persistence.webstore.preco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PrecoJpaRepository extends JpaRepository<PrecoEntity, Long>, JpaSpecificationExecutor<PrecoEntity> {

    Optional<PrecoEntity> findByUuid(String uuid);
}
