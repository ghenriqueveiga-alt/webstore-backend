package com.hvs.webstore.back.app.command.webstore.video;

public record PatchVideoCommand(Long aId,
                                String aUuid,
                                String aStatusCode,
                                String aNome,
                                String aCaminho,
                                String aExtensao,
                                String aTamanho,
                                String aDuracao,
                                String aResolucao,
                                Long aProdutoId) {

    public static PatchVideoCommand from(final Long aId,
                                         final PatchVideoCommand aInput) {

        return new PatchVideoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aCaminho,
                aInput.aExtensao,
                aInput.aTamanho,
                aInput.aDuracao,
                aInput.aResolucao,
                aInput.aProdutoId);
    }

    public static PatchVideoCommand from(final String aUuid,
                                         final PatchVideoCommand aInput) {

        return new PatchVideoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aCaminho,
                aInput.aExtensao,
                aInput.aTamanho,
                aInput.aDuracao,
                aInput.aResolucao,
                aInput.aProdutoId);
    }
}
