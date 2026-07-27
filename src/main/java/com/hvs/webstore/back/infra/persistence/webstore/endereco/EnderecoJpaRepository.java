package com.hvs.webstore.back.infra.persistence.webstore.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EnderecoJpaRepository extends JpaRepository<EnderecoEntity, Long>, JpaSpecificationExecutor<EnderecoEntity> {

    Optional<EnderecoEntity> findByUuid(String uuid);
}
