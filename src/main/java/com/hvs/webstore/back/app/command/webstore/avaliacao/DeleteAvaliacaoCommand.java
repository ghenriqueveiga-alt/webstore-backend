package com.hvs.webstore.back.app.command.webstore.avaliacao;

public record DeleteAvaliacaoCommand(Long aId,
                                     String aUuid) {

    public static DeleteAvaliacaoCommand from(final Long aId) {

        return new DeleteAvaliacaoCommand(
                aId,
                null);
    }

    public static DeleteAvaliacaoCommand from(final String aUuid) {

        return new DeleteAvaliacaoCommand(
                null,
                aUuid);
    }
}
