package com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class NotificacaoTemplate extends Entity<NotificacaoTemplateId> {

    private final NotificacaoTemplateUuid uuid;
    private final NotificacaoTemplateStatus statusCode;
    private final String nome;
    private final TipoNotificacaoTemplate tipo;
    private final String assunto;
    private final String corpo;
    private final String variaveis;

    private NotificacaoTemplate(final NotificacaoTemplateId id,
                                final NotificacaoTemplateUuid uuid,
                                final NotificacaoTemplateStatus statusCode,
                                final String nome,
                                final TipoNotificacaoTemplate tipo,
                                final String assunto,
                                final String corpo,
                                final String variaveis) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nome = nome;
        this.tipo = tipo;
        this.assunto = assunto;
        this.corpo = corpo;
        this.variaveis = variaveis;
    }

    public static NotificacaoTemplate create(final String aNome,
                                             final TipoNotificacaoTemplate aTipo,
                                             final String aAssunto,
                                             final String aCorpo,
                                             final String aVariaveis) {

        return new NotificacaoTemplate(
                NotificacaoTemplateId.from(-1L),
                NotificacaoTemplateUuid.unique(),
                NotificacaoTemplateStatus.ACTIVE,
                aNome,
                aTipo,
                aAssunto,
                aCorpo,
                aVariaveis);
    }

    public static NotificacaoTemplate update(final Long aId,
                                             final String aUuid,
                                             final String aStatusCode,
                                             final String aNome,
                                             final TipoNotificacaoTemplate aTipo,
                                             final String aAssunto,
                                             final String aCorpo,
                                             final String aVariaveis) {

        return new NotificacaoTemplate(
                aId != null ? NotificacaoTemplateId.from(aId) : null,
                aUuid != null ? NotificacaoTemplateUuid.from(aUuid) : null,
                aStatusCode != null ? NotificacaoTemplateStatus.findByCode(aStatusCode) : null,
                aNome,
                aTipo,
                aAssunto,
                aCorpo,
                aVariaveis);
    }

    public static NotificacaoTemplate patch(final String aStatusCode,
                                            final String aNome,
                                            final TipoNotificacaoTemplate aTipo,
                                            final String aAssunto,
                                            final String aCorpo,
                                            final String aVariaveis,
                                            final NotificacaoTemplate aExisting) {

        return new NotificacaoTemplate(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? NotificacaoTemplateStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aNome != null ? aNome : aExisting.getNome(),
                aTipo != null ? aTipo : aExisting.getTipo(),
                aAssunto != null ? aAssunto : aExisting.getAssunto(),
                aCorpo != null ? aCorpo : aExisting.getCorpo(),
                aVariaveis != null ? aVariaveis : aExisting.getVariaveis());
    }

    public static NotificacaoTemplate from(final Long aId,
                                           final String aUuid,
                                           final String aStatusDesc,
                                           final String aNome,
                                           final String aTipoDesc,
                                           final String aAssunto,
                                           final String aCorpo,
                                           final String aVariaveis) {

        return new NotificacaoTemplate(
                aId != null ? NotificacaoTemplateId.from(aId) : null,
                aUuid != null ? NotificacaoTemplateUuid.from(aUuid) : null,
                aStatusDesc != null ? NotificacaoTemplateStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aTipoDesc != null ? TipoNotificacaoTemplate.findByDesc(aTipoDesc) : null,
                aAssunto,
                aCorpo,
                aVariaveis);
    }

    public static NotificacaoTemplate from(final Long aId) {

        return new NotificacaoTemplate(
                aId != null ? NotificacaoTemplateId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static NotificacaoTemplate from(final String aUuid) {

        return new NotificacaoTemplate(
                null,
                aUuid != null ? NotificacaoTemplateUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new NotificacaoTemplateValidator(aHandler, this).validate();
    }

    public NotificacaoTemplateUuid getUuid() {
        return uuid;
    }
    public NotificacaoTemplateStatus getStatusCode() {
        return statusCode;
    }
    public String getNome() {
        return nome;
    }
    public TipoNotificacaoTemplate getTipo() {
        return tipo;
    }
    public String getAssunto() {
        return assunto;
    }
    public String getCorpo() {
        return corpo;
    }
    public String getVariaveis() {
        return variaveis;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        NotificacaoTemplate that = (NotificacaoTemplate) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(nome, that.nome) &&
                tipo == that.tipo &&
                Objects.equals(assunto, that.assunto) &&
                Objects.equals(corpo, that.corpo) &&
                Objects.equals(variaveis, that.variaveis);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nome,
                tipo,
                assunto,
                corpo,
                variaveis);
    }
}
