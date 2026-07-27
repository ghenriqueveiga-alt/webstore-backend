package com.hvs.webstore.back.app.command.webstore.avaliacao;

public record PatchAvaliacaoCommand(Long aId,
                                    String aUuid,
                                    String aStatusCode,
                                    Long aProdutoId,
                                    Long aUsuarioId,
                                    Integer aNota,
                                    String aTitulo,
                                    String aComentario) {

    public static PatchAvaliacaoCommand from(final Long aId,
                                             final PatchAvaliacaoCommand aInput) {

        return new PatchAvaliacaoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aProdutoId,
                aInput.aUsuarioId,
                aInput.aNota,
                aInput.aTitulo,
                aInput.aComentario);
    }

    public static PatchAvaliacaoCommand from(final String aUuid,
                                             final PatchAvaliacaoCommand aInput) {

        return new PatchAvaliacaoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aProdutoId,
                aInput.aUsuarioId,
                aInput.aNota,
                aInput.aTitulo,
                aInput.aComentario);
    }
}
