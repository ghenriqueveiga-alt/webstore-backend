package com.hvs.webstore.back.app.command.television.arquivo;

public record CreateArquivoCommand(String aNome,
                                   String aTipoCode,
                                   Long aTamanho,
                                   String aCaminho) {

    public static CreateArquivoCommand from(final String aNome,
                                            final String aTipoCode,
                                            final Long aTamanho,
                                            final String aCaminho) {

        return new CreateArquivoCommand(
                aNome,
                aTipoCode,
                aTamanho,
                aCaminho);
    }
}
