package com.hvs.webstore.back.infra.persistence.television.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface GradeJpaRepository extends JpaRepository<GradeEntity, Long>, JpaSpecificationExecutor<GradeEntity> {

    Optional<GradeEntity> findByUuid(String uuid);
}
