package com.hvs.webstore.back.app.command.webstore.notificacaotemplate;

public record UpdateNotificacaoTemplateCommand(Long aId,
                                               String aUuid,
                                               String aStatusCode,
                                               String aNome,
                                               String aTipoCode,
                                               String aAssunto,
                                               String aCorpo,
                                               String aVariaveis) {

    public static UpdateNotificacaoTemplateCommand from(final Long aId,
                                                        final UpdateNotificacaoTemplateCommand aInput) {

        return new UpdateNotificacaoTemplateCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aTipoCode,
                aInput.aAssunto,
                aInput.aCorpo,
                aInput.aVariaveis
        );
    }

    public static UpdateNotificacaoTemplateCommand from(final String aUuid,
                                                        final UpdateNotificacaoTemplateCommand aInput) {

        return new UpdateNotificacaoTemplateCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aTipoCode,
                aInput.aAssunto,
                aInput.aCorpo,
                aInput.aVariaveis
        );
    }
}
