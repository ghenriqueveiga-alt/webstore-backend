package com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class PreferenciaNotificacao extends Entity<PreferenciaNotificacaoId> {

    private final PreferenciaNotificacaoUuid uuid;
    private final PreferenciaNotificacaoStatus statusCode;
    private final Usuario usuario;
    private final TipoNotificacao tipo;
    private final Boolean ativo;

    private PreferenciaNotificacao(final PreferenciaNotificacaoId id,
                                    final PreferenciaNotificacaoUuid uuid,
                                    final PreferenciaNotificacaoStatus statusCode,
                                    final Usuario usuario,
                                    final TipoNotificacao tipo,
                                    final Boolean ativo) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.usuario = usuario;
        this.tipo = tipo;
        this.ativo = ativo;
    }

    public static PreferenciaNotificacao create(final Long aUsuarioId,
                                                 final String aTipoCode,
                                                 final Boolean aAtivo) {

        final var id = PreferenciaNotificacaoId.from(-1L);
        final var uuid = PreferenciaNotificacaoUuid.unique();
        final var status = PreferenciaNotificacaoStatus.ACTIVE;
        final var usuario = aUsuarioId != null ? Usuario.from(aUsuarioId) : null;
        final var tipo = aTipoCode != null ? TipoNotificacao.findByCode(aTipoCode) : null;

        return new PreferenciaNotificacao(
                id,
                uuid,
                status,
                usuario,
                tipo,
                aAtivo);
    }

    public static PreferenciaNotificacao update(final Long aId,
                                                 final String aUuid,
                                                 final String aStatusCode,
                                                 final Long aUsuarioId,
                                                 final String aTipoCode,
                                                 final Boolean aAtivo) {

        final var id = aId != null ? PreferenciaNotificacaoId.from(aId) : null;
        final var uuid = aUuid != null ? PreferenciaNotificacaoUuid.from(aUuid) : null;
        final var status = aStatusCode != null ? PreferenciaNotificacaoStatus.findByCode(aStatusCode) : null;
        final var usuario = aUsuarioId != null ? Usuario.from(aUsuarioId) : null;
        final var tipo = aTipoCode != null ? TipoNotificacao.findByCode(aTipoCode) : null;

        return new PreferenciaNotificacao(
                id,
                uuid,
                status,
                usuario,
                tipo,
                aAtivo);
    }

    public static PreferenciaNotificacao patch(final String aStatusCode,
                                                final Long aUsuarioId,
                                                final String aTipoCode,
                                                final Boolean aAtivo,
                                                final PreferenciaNotificacao aExisting) {

        final var id = aExisting.getId();
        final var uuid = aExisting.getUuid();
        final var status = aStatusCode != null ? PreferenciaNotificacaoStatus.findByCode(aStatusCode) : aExisting.getStatusCode();
        final var usuario = aUsuarioId != null ? Usuario.from(aUsuarioId) : aExisting.getUsuario();
        final var tipo = aTipoCode != null ? TipoNotificacao.findByCode(aTipoCode) : aExisting.getTipo();
        final var ativo = aAtivo != null ? aAtivo : aExisting.getAtivo();

        return new PreferenciaNotificacao(
                id,
                uuid,
                status,
                usuario,
                tipo,
                ativo);
    }

    public static PreferenciaNotificacao from(final Long aId,
                                               final String aUuid,
                                               final String aStatusDesc,
                                               final Usuario aUsuario,
                                               final String aTipoDesc,
                                               final Boolean aAtivo) {

        final var id = aId != null ? PreferenciaNotificacaoId.from(aId) : null;
        final var uuid = aUuid != null ? PreferenciaNotificacaoUuid.from(aUuid) : null;
        final var status = aStatusDesc != null ? PreferenciaNotificacaoStatus.findByDesc(aStatusDesc) : null;
        final var tipo = aTipoDesc != null ? TipoNotificacao.findByDesc(aTipoDesc) : null;

        return new PreferenciaNotificacao(
                id,
                uuid,
                status,
                aUsuario,
                tipo,
                aAtivo);
    }

    public static PreferenciaNotificacao from(final Long aId) {

        return new PreferenciaNotificacao(
                aId != null ? PreferenciaNotificacaoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null);
    }

    public static PreferenciaNotificacao from(final String aUuid) {

        return new PreferenciaNotificacao(
                null,
                aUuid != null ? PreferenciaNotificacaoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new PreferenciaNotificacaoValidator(aHandler, this).validate();
    }

    public PreferenciaNotificacaoUuid getUuid() {
        return uuid;
    }
    public PreferenciaNotificacaoStatus getStatusCode() {
        return statusCode;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public TipoNotificacao getTipo() {
        return tipo;
    }
    public Boolean getAtivo() {
        return ativo;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        PreferenciaNotificacao that = (PreferenciaNotificacao) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(usuario, that.usuario) &&
                tipo == that.tipo &&
                Objects.equals(ativo, that.ativo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                usuario,
                tipo,
                ativo);
    }
}
