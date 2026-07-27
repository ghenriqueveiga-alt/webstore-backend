package com.hvs.webstore.back.app.command.webstore.video;

public record UpdateVideoCommand(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 String aNome,
                                 String aCaminho,
                                 String aExtensao,
                                 String aTamanho,
                                 String aDuracao,
                                 String aResolucao,
                                 Long aProdutoId) {

    public static UpdateVideoCommand from(final Long aId,
                                          final UpdateVideoCommand aInput) {

        return new UpdateVideoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aCaminho,
                aInput.aExtensao,
                aInput.aTamanho,
                aInput.aDuracao,
                aInput.aResolucao,
                aInput.aProdutoId
        );
    }

    public static UpdateVideoCommand from(final String aUuid,
                                          final UpdateVideoCommand aInput) {

        return new UpdateVideoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aCaminho,
                aInput.aExtensao,
                aInput.aTamanho,
                aInput.aDuracao,
                aInput.aResolucao,
                aInput.aProdutoId
        );
    }
}
