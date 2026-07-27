package com.hvs.webstore.back.app.command.webstore.notificacaotemplate;

public record DeleteNotificacaoTemplateCommand(Long aId,
                                               String aUuid) {

    public static DeleteNotificacaoTemplateCommand from(final Long aId) {

        return new DeleteNotificacaoTemplateCommand(
                aId,
                null);
    }

    public static DeleteNotificacaoTemplateCommand from(final String aUuid) {

        return new DeleteNotificacaoTemplateCommand(
                null,
                aUuid);
    }
}
