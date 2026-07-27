package com.hvs.webstore.back.app.command.webstore.imagem;

public record PatchImagemCommand(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 String aNome,
                                 String aCaminho,
                                 String aExtensao,
                                 String aTamanho,
                                 String aResolucao) {

    public static PatchImagemCommand from(final Long aId,
                                          final PatchImagemCommand aInput) {

        return new PatchImagemCommand(
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

    public static PatchImagemCommand from(final String aUuid,
                                          final PatchImagemCommand aInput) {

        return new PatchImagemCommand(
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
