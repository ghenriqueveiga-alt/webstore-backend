package com.hvs.webstore.back.infra.persistence.webstore.notificacaotemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface NotificacaoTemplateJpaRepository extends JpaRepository<NotificacaoTemplateEntity, Long>, JpaSpecificationExecutor<NotificacaoTemplateEntity> {

    Optional<NotificacaoTemplateEntity> findByUuid(String uuid);
    List<NotificacaoTemplateEntity> findByTipoDesc(String tipoDesc);
}
