package com.hvs.webstore.back.app.command.webstore.anexo;

public record CreateAnexoCommand(String aEntidadeNome,
                                 Long aEntidadeId,
                                 String aNome,
                                 String aTipo,
                                 Long aTamanho,
                                 String aUrl) {

    public static CreateAnexoCommand from(final String aEntidadeNome,
                                          final Long aEntidadeId,
                                          final String aNome,
                                          final String aTipo,
                                          final Long aTamanho,
                                          final String aUrl) {

        return new CreateAnexoCommand(
                aEntidadeNome,
                aEntidadeId,
                aNome,
                aTipo,
                aTamanho,
                aUrl);
    }
}
