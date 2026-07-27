package com.hvs.webstore.back.app.command.webstore.notificacaotemplate;

public record PatchNotificacaoTemplateCommand(Long aId,
                                              String aUuid,
                                              String aStatusCode,
                                              String aNome,
                                              String aTipoCode,
                                              String aAssunto,
                                              String aCorpo,
                                              String aVariaveis) {

    public static PatchNotificacaoTemplateCommand from(final Long aId,
                                                       final PatchNotificacaoTemplateCommand aInput) {

        return new PatchNotificacaoTemplateCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aTipoCode,
                aInput.aAssunto,
                aInput.aCorpo,
                aInput.aVariaveis);
    }

    public static PatchNotificacaoTemplateCommand from(final String aUuid,
                                                       final PatchNotificacaoTemplateCommand aInput) {

        return new PatchNotificacaoTemplateCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aTipoCode,
                aInput.aAssunto,
                aInput.aCorpo,
                aInput.aVariaveis);
    }
}
