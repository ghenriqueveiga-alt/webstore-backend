package com.hvs.webstore.back.infra.persistence.webstore.precopromocional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.List;

public interface PrecoPromocionalJpaRepository extends JpaRepository<PrecoPromocionalEntity, Long>, JpaSpecificationExecutor<PrecoPromocionalEntity> {

    Optional<PrecoPromocionalEntity> findByUuid(String uuid);
    List<PrecoPromocionalEntity> findByProduto_Id(Long produtoId);
}
