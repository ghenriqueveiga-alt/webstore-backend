package com.hvs.webstore.back.infra.persistence.webstore.preferencianotificacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PreferenciaNotificacaoJpaRepository extends JpaRepository<PreferenciaNotificacaoEntity, Long>, JpaSpecificationExecutor<PreferenciaNotificacaoEntity> {

    Optional<PreferenciaNotificacaoEntity> findByUuid(String uuid);
    List<PreferenciaNotificacaoEntity> findByUsuario_Id(Long usuarioId);
    Optional<PreferenciaNotificacaoEntity> findByUsuario_IdAndTipoDesc(Long usuarioId, String tipoDesc);
}
