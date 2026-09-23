package com.hvs.ws.back.app.command.arquivo;

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
