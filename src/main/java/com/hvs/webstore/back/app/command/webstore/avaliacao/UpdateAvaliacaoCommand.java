package com.hvs.webstore.back.app.command.webstore.avaliacao;

public record UpdateAvaliacaoCommand(Long aId,
                                     String aUuid,
                                     String aStatusCode,
                                     Long aProdutoId,
                                     Long aUsuarioId,
                                     Integer aNota,
                                     String aTitulo,
                                     String aComentario) {

    public static UpdateAvaliacaoCommand from(final Long aId,
                                              final UpdateAvaliacaoCommand aInput) {

        return new UpdateAvaliacaoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aProdutoId,
                aInput.aUsuarioId,
                aInput.aNota,
                aInput.aTitulo,
                aInput.aComentario
        );
    }

    public static UpdateAvaliacaoCommand from(final String aUuid,
                                              final UpdateAvaliacaoCommand aInput) {

        return new UpdateAvaliacaoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aProdutoId,
                aInput.aUsuarioId,
                aInput.aNota,
                aInput.aTitulo,
                aInput.aComentario
        );
    }
}
