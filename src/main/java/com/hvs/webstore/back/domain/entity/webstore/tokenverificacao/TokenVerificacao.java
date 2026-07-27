package com.hvs.webstore.back.domain.entity.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class TokenVerificacao extends Entity<TokenVerificacaoId> {

    private final TokenVerificacaoUuid uuid;
    private final TokenVerificacaoStatus statusCode;
    private final Usuario usuario;
    private final String token;
    private final TipoToken tipoToken;
    private final Instant expiradoEm;
    private final Instant utilizadoEm;
    private final Instant criadoEm;

    private TokenVerificacao(final TokenVerificacaoId id,
                              final TokenVerificacaoUuid uuid,
                              final TokenVerificacaoStatus statusCode,
                              final Usuario usuario,
                              final String token,
                              final TipoToken tipoToken,
                              final Instant expiradoEm,
                              final Instant utilizadoEm,
                              final Instant criadoEm) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.usuario = usuario;
        this.token = token;
        this.tipoToken = tipoToken;
        this.expiradoEm = expiradoEm;
        this.utilizadoEm = utilizadoEm;
        this.criadoEm = criadoEm;
    }

    public static TokenVerificacao create(final Long aUsuarioId,
                                           final String aToken,
                                           final String aTipoCode,
                                           final Instant aExpiradoEm) {

        return new TokenVerificacao(
                TokenVerificacaoId.from(-1L),
                TokenVerificacaoUuid.unique(),
                TokenVerificacaoStatus.ACTIVE,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                aToken,
                aTipoCode != null ? TipoToken.findByCode(aTipoCode) : null,
                aExpiradoEm,
                null,
                Instant.now());
    }

    public static TokenVerificacao update(final Long aId,
                                           final String aUuid,
                                           final String aStatusCode,
                                           final Long aUsuarioId,
                                           final String aToken,
                                           final String aTipoCode,
                                           final Instant aExpiradoEm,
                                           final Instant aUtilizadoEm,
                                           final Instant aCriadoEm) {

        return new TokenVerificacao(
                aId != null ? TokenVerificacaoId.from(aId) : null,
                aUuid != null ? TokenVerificacaoUuid.from(aUuid) : null,
                aStatusCode != null ? TokenVerificacaoStatus.findByCode(aStatusCode) : null,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                aToken,
                aTipoCode != null ? TipoToken.findByCode(aTipoCode) : null,
                aExpiradoEm,
                aUtilizadoEm,
                aCriadoEm);
    }

    public static TokenVerificacao patch(final String aStatusCode,
                                          final Long aUsuarioId,
                                          final String aToken,
                                          final String aTipoCode,
                                          final Instant aExpiradoEm,
                                          final Instant aUtilizadoEm,
                                          final TokenVerificacao aExisting) {

        return new TokenVerificacao(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? TokenVerificacaoStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aUsuarioId != null ? Usuario.from(aUsuarioId) : aExisting.getUsuario(),
                aToken != null ? aToken : aExisting.getToken(),
                aTipoCode != null ? TipoToken.findByCode(aTipoCode) : aExisting.getTipoToken(),
                aExpiradoEm != null ? aExpiradoEm : aExisting.getExpiradoEm(),
                aUtilizadoEm != null ? aUtilizadoEm : aExisting.getUtilizadoEm(),
                aExisting.getCriadoEm());
    }

    public static TokenVerificacao from(final Long aId,
                                         final String aUuid,
                                         final String aStatusDesc,
                                         final Usuario aUsuario,
                                         final String aToken,
                                         final String aTipoDesc,
                                         final Instant aExpiradoEm,
                                         final Instant aUtilizadoEm,
                                         final Instant aCriadoEm) {

        return new TokenVerificacao(
                aId != null ? TokenVerificacaoId.from(aId) : null,
                aUuid != null ? TokenVerificacaoUuid.from(aUuid) : null,
                aStatusDesc != null ? TokenVerificacaoStatus.findByDesc(aStatusDesc) : null,
                aUsuario,
                aToken,
                aTipoDesc != null ? TipoToken.findByDesc(aTipoDesc) : null,
                aExpiradoEm,
                aUtilizadoEm,
                aCriadoEm);
    }

    public static TokenVerificacao from(final Long aId) {

        return new TokenVerificacao(
                aId != null ? TokenVerificacaoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static TokenVerificacao from(final String aUuid) {

        return new TokenVerificacao(
                null,
                aUuid != null ? TokenVerificacaoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new TokenVerificacaoValidator(aHandler, this).validate();
    }

    public TokenVerificacaoUuid getUuid() {
        return uuid;
    }
    public TokenVerificacaoStatus getStatusCode() {
        return statusCode;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public String getToken() {
        return token;
    }
    public TipoToken getTipoToken() {
        return tipoToken;
    }
    public Instant getExpiradoEm() {
        return expiradoEm;
    }
    public Instant getUtilizadoEm() {
        return utilizadoEm;
    }
    public Instant getCriadoEm() {
        return criadoEm;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        TokenVerificacao that = (TokenVerificacao) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(usuario, that.usuario) &&
                Objects.equals(token, that.token) &&
                tipoToken == that.tipoToken &&
                Objects.equals(expiradoEm, that.expiradoEm) &&
                Objects.equals(utilizadoEm, that.utilizadoEm) &&
                Objects.equals(criadoEm, that.criadoEm);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                usuario,
                token,
                tipoToken,
                expiradoEm,
                utilizadoEm,
                criadoEm);
    }
}
