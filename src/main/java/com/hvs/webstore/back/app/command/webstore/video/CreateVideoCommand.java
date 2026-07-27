package com.hvs.webstore.back.app.command.webstore.video;

public record CreateVideoCommand(String aNome,
                                 String aCaminho,
                                 String aExtensao,
                                 String aTamanho,
                                 String aDuracao,
                                 String aResolucao,
                                 Long aProdutoId) {

    public static CreateVideoCommand from(final String aNome,
                                          final String aCaminho,
                                          final String aExtensao,
                                          final String aTamanho,
                                          final String aDuracao,
                                          final String aResolucao,
                                          final Long aProdutoId) {

        return new CreateVideoCommand(
                aNome,
                aCaminho,
                aExtensao,
                aTamanho,
                aDuracao,
                aResolucao,
                aProdutoId);
    }
}
