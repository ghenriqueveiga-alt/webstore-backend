package com.hvs.webstore.back.app.command.webstore.notificacaotemplate;

public record ReadByTipoNotificacaoTemplateCommand(String aTipoCode) {

    public static ReadByTipoNotificacaoTemplateCommand from(final String aTipoCode) {

        return new ReadByTipoNotificacaoTemplateCommand(aTipoCode);
    }
}
