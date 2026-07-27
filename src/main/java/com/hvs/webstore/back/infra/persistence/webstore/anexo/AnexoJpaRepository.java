package com.hvs.webstore.back.infra.persistence.webstore.anexo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AnexoJpaRepository extends JpaRepository<AnexoEntity, Long>, JpaSpecificationExecutor<AnexoEntity> {

    Optional<AnexoEntity> findByUuid(String uuid);
    List<AnexoEntity> findByEntidadeAndEntidadeId(String entidade, Long entidadeId);
}
