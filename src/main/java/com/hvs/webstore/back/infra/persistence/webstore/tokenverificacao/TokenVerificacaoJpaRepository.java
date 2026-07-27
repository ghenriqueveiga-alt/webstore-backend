package com.hvs.webstore.back.infra.persistence.webstore.tokenverificacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface TokenVerificacaoJpaRepository extends JpaRepository<TokenVerificacaoEntity, Long>, JpaSpecificationExecutor<TokenVerificacaoEntity> {

    Optional<TokenVerificacaoEntity> findByUuid(String uuid);
    Optional<TokenVerificacaoEntity> findByToken(String token);
    List<TokenVerificacaoEntity> findByUsuario_Id(Long usuarioId);
}
