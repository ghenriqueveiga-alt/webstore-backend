package com.hvs.webstore.back.infra.persistence.webstore.listadesejos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ListaDesejosJpaRepository extends JpaRepository<ListaDesejosEntity, Long>, JpaSpecificationExecutor<ListaDesejosEntity> {

    Optional<ListaDesejosEntity> findByUuid(String uuid);
    Optional<ListaDesejosEntity> findByUsuarioId(Long usuarioId);
}
