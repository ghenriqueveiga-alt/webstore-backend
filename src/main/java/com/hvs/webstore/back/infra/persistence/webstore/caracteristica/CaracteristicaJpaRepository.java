package com.hvs.webstore.back.infra.persistence.webstore.caracteristica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CaracteristicaJpaRepository extends JpaRepository<CaracteristicaEntity, Long>, JpaSpecificationExecutor<CaracteristicaEntity> {

    Optional<CaracteristicaEntity> findByUuid(String uuid);
}
