package com.hvs.webstore.back.infra.persistence.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "token_verificacao")
public class TokenVerificacaoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
    private String token;
    private String tipoDesc;
    private Instant expiradoEm;
    private Instant utilizadoEm;
    private Instant criadoEm;

    public TokenVerificacaoEntity() {

    }

    public TokenVerificacaoEntity(final Long id,
                                   final String uuid,
                                   final String statusDesc,
                                   final UsuarioEntity usuario,
                                   final String token,
                                   final String tipoDesc,
                                   final Instant expiradoEm,
                                   final Instant utilizadoEm,
                                   final Instant criadoEm) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.usuario = usuario;
        this.token = token;
        this.tipoDesc = tipoDesc;
        this.expiradoEm = expiradoEm;
        this.utilizadoEm = utilizadoEm;
        this.criadoEm = criadoEm;
    }

    public static TokenVerificacaoEntity from(TokenVerificacao aTokenVerificacao) {

        return new TokenVerificacaoEntity(
                aTokenVerificacao.getId().getValue() < 0 ? null : aTokenVerificacao.getId().getValue(),
                aTokenVerificacao.getUuid().getValue(),
                aTokenVerificacao.getStatusCode().getDesc(),
                aTokenVerificacao.getUsuario() != null ? UsuarioEntity.from(aTokenVerificacao.getUsuario().getId().getValue()) : null,
                aTokenVerificacao.getToken(),
                aTokenVerificacao.getTipoToken().getCode(),
                aTokenVerificacao.getExpiradoEm(),
                aTokenVerificacao.getUtilizadoEm(),
                aTokenVerificacao.getCriadoEm()
        );
    }

    public static TokenVerificacaoEntity from(final Long aTokenVerificacaoId) {

        final var tokenVerificacao = new TokenVerificacaoEntity();
        tokenVerificacao.setId(aTokenVerificacaoId);

        return tokenVerificacao;
    }

    public TokenVerificacao toDomain() {

        return TokenVerificacao.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainChildren() : null,
                token,
                tipoDesc,
                expiradoEm,
                utilizadoEm,
                criadoEm
        );
    }

    public TokenVerificacao toDomainChildren() {

        return TokenVerificacao.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainSimple() : null,
                token,
                tipoDesc,
                expiradoEm,
                utilizadoEm,
                criadoEm
        );
    }

    public TokenVerificacao toDomainSimple() {

        return TokenVerificacao.from(
                getId(),
                uuid,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
