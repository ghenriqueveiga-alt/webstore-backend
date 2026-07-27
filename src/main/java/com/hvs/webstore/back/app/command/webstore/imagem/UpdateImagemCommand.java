package com.hvs.webstore.back.app.command.webstore.imagem;

public record UpdateImagemCommand(Long aId,
                                  String aUuid,
                                  String aStatusCode,
                                  String aNome,
                                  String aCaminho,
                                  String aExtensao,
                                  String aTamanho,
                                  String aResolucao) {

    public static UpdateImagemCommand from(final Long aId,
                                           final UpdateImagemCommand aInput) {

        return new UpdateImagemCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aCaminho,
                aInput.aExtensao,
                aInput.aTamanho,
                aInput.aResolucao
        );
    }

    public static UpdateImagemCommand from(final String aUuid,
                                           final UpdateImagemCommand aInput) {

        return new UpdateImagemCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aCaminho,
                aInput.aExtensao,
                aInput.aTamanho,
                aInput.aResolucao
        );
    }
}
