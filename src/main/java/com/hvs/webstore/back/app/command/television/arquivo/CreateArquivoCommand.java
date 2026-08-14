package com.hvs.webstore.back.app.command.television.arquivo;

public record CreateArquivoCommand(String aNome,
                                   String aTipoCode,
                                   Long aTamanho,
                                   String aCaminho,
                                   String aDuracao) {

    public static CreateArquivoCommand from(final String aNome,
                                            final String aTipoCode,
                                            final Long aTamanho,
                                            final String aCaminho,
                                            final String aDuracao) {

        return new CreateArquivoCommand(
                aNome,
                aTipoCode,
                aTamanho,
                aCaminho,
                aDuracao);
    }
}