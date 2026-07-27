package com.hvs.webstore.back.infra.persistence.webstore.boleto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BoletoJpaRepository extends JpaRepository<BoletoEntity, Long>, JpaSpecificationExecutor<BoletoEntity> {

    Optional<BoletoEntity> findByUuid(String uuid);
}
