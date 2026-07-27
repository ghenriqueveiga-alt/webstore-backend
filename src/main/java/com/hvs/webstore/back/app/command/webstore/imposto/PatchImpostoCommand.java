package com.hvs.webstore.back.app.command.webstore.imposto;

public record PatchImpostoCommand(Long aId,
                                  String aUuid,
                                  String aStatusCode,
                                  String aNome,
                                  String aTipoCode,
                                  Integer aAliquota,
                                  String aDescricao) {

    public static PatchImpostoCommand from(final Long aId,
                                           final PatchImpostoCommand aIn) {

        return new PatchImpostoCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aNome,
                aIn.aTipoCode,
                aIn.aAliquota,
                aIn.aDescricao);
    }

    public static PatchImpostoCommand from(final String aUuid,
                                           final PatchImpostoCommand aIn) {

        return new PatchImpostoCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aNome,
                aIn.aTipoCode,
                aIn.aAliquota,
                aIn.aDescricao);
    }
}
