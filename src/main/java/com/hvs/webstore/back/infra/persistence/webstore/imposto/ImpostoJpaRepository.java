package com.hvs.webstore.back.infra.persistence.webstore.imposto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ImpostoJpaRepository extends JpaRepository<ImpostoEntity, Long>, JpaSpecificationExecutor<ImpostoEntity> {

    Optional<ImpostoEntity> findByUuid(String uuid);
}
