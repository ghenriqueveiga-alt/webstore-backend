package com.hvs.webstore.back.app.command.webstore.imagem;

public record DeleteImagemCommand(Long aId,
                                  String aUuid) {

    public static DeleteImagemCommand from(final Long aId) {

        return new DeleteImagemCommand(
                aId,
                null);
    }

    public static DeleteImagemCommand from(final String aUuid) {

        return new DeleteImagemCommand(
                null,
                aUuid);
    }
}
