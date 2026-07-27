package com.hvs.webstore.back.app.command.webstore.avaliacao;

public record ReadAvaliacaoCommand(Long aId,
                                   String aUuid) {

    public static ReadAvaliacaoCommand from(final Long aId) {

        return new ReadAvaliacaoCommand(
                aId,
                null
        );
    }

    public static ReadAvaliacaoCommand from(final String aUuid) {

        return new ReadAvaliacaoCommand(
                null,
                aUuid
        );
    }
}
