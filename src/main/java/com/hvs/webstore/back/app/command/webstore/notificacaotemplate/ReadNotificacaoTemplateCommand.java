package com.hvs.webstore.back.app.command.webstore.notificacaotemplate;

public record ReadNotificacaoTemplateCommand(Long aId,
                                             String aUuid) {

    public static ReadNotificacaoTemplateCommand from(final Long aId) {

        return new ReadNotificacaoTemplateCommand(
                aId,
                null
        );
    }

    public static ReadNotificacaoTemplateCommand from(final String aUuid) {

        return new ReadNotificacaoTemplateCommand(
                null,
                aUuid
        );
    }
}
