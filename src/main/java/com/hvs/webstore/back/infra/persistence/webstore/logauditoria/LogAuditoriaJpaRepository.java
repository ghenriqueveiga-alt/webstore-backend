package com.hvs.webstore.back.infra.persistence.webstore.logauditoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface LogAuditoriaJpaRepository extends JpaRepository<LogAuditoriaEntity, Long>, JpaSpecificationExecutor<LogAuditoriaEntity> {

    Optional<LogAuditoriaEntity> findByUuid(String uuid);
    List<LogAuditoriaEntity> findByEntidadeAndEntidadeId(String entidade, Long entidadeId);
    List<LogAuditoriaEntity> findByUsuario_Id(Long usuarioId);
}
