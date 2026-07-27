package com.hvs.webstore.back.app.command.webstore.notificacaotemplate;

public record CreateNotificacaoTemplateCommand(String aNome,
                                               String aTipoCode,
                                               String aAssunto,
                                               String aCorpo,
                                               String aVariaveis) {

    public static CreateNotificacaoTemplateCommand from(final String aNome,
                                                        final String aTipoCode,
                                                        final String aAssunto,
                                                        final String aCorpo,
                                                        final String aVariaveis) {

        return new CreateNotificacaoTemplateCommand(
                aNome,
                aTipoCode,
                aAssunto,
                aCorpo,
                aVariaveis);
    }
}
