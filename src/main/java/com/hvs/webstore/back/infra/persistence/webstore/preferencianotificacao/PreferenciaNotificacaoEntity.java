package com.hvs.webstore.back.infra.persistence.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "preferencia_notificacao")
public class PreferenciaNotificacaoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String tipoDesc;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public PreferenciaNotificacaoEntity() {

    }

    public PreferenciaNotificacaoEntity(final Long id,
                                         final String uuid,
                                         final String statusDesc,
                                         final String tipoDesc,
                                         final Boolean ativo,
                                         final UsuarioEntity usuario) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.tipoDesc = tipoDesc;
        this.ativo = ativo;
        this.usuario = usuario;
    }

    public static PreferenciaNotificacaoEntity from(PreferenciaNotificacao aPreferenciaNotificacao) {

        return new PreferenciaNotificacaoEntity(
                aPreferenciaNotificacao.getId().getValue() < 0 ? null : aPreferenciaNotificacao.getId().getValue(),
                aPreferenciaNotificacao.getUuid().getValue(),
                aPreferenciaNotificacao.getStatusCode().getDesc(),
                aPreferenciaNotificacao.getTipo().getDesc(),
                aPreferenciaNotificacao.getAtivo(),
                aPreferenciaNotificacao.getUsuario() != null ? UsuarioEntity.from(aPreferenciaNotificacao.getUsuario().getId().getValue()) : null
        );
    }

    public static PreferenciaNotificacaoEntity from(final Long aPreferenciaNotificacaoId) {

        final var entity = new PreferenciaNotificacaoEntity();
        entity.setId(aPreferenciaNotificacaoId);

        return entity;
    }

    public PreferenciaNotificacao toDomain() {

        return PreferenciaNotificacao.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainChildren() : null,
                tipoDesc,
                ativo
        );
    }

    public PreferenciaNotificacao toDomainChildren() {

        return PreferenciaNotificacao.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainSimple() : null,
                tipoDesc,
                ativo
        );
    }

    public PreferenciaNotificacao toDomainSimple() {

        return PreferenciaNotificacao.from(
                getId(),
                uuid,
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
