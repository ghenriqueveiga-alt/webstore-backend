package com.hvs.webstore.back.app.command.webstore.imagem;

public record CreateImagemCommand(String aNome,
                                  String aCaminho,
                                  String aExtensao,
                                  String aTamanho,
                                  String aResolucao) {

    public static CreateImagemCommand from(final String aNome,
                                           final String aCaminho,
                                           final String aExtensao,
                                           final String aTamanho,
                                           final String aResolucao) {

        return new CreateImagemCommand(
                aNome,
                aCaminho,
                aExtensao,
                aTamanho,
                aResolucao);
    }
}
