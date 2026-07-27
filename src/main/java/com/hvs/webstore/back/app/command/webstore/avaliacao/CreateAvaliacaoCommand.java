package com.hvs.webstore.back.app.command.webstore.avaliacao;

public record CreateAvaliacaoCommand(Long aProdutoId,
                                     Long aUsuarioId,
                                     Integer nota,
                                     String titulo,
                                     String comentario) {

    public static CreateAvaliacaoCommand from(final Long aProdutoId,
                                              final Long aUsuarioId,
                                              final Integer nota,
                                              final String titulo,
                                              final String comentario) {

        return new CreateAvaliacaoCommand(
                aProdutoId,
                aUsuarioId,
                nota,
                titulo,
                comentario);
    }
}
