package com.hvs.webstore.back.app.command.webstore.imposto;

public record UpdateImpostoCommand(Long aId,
                                   String aUuid,
                                   String aStatusCode,
                                   String aNome,
                                   String aTipoCode,
                                   Integer aAliquota,
                                   String aDescricao) {

    public static UpdateImpostoCommand from(final Long aId,
                                            final UpdateImpostoCommand aIn) {

        return new UpdateImpostoCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aNome,
                aIn.aTipoCode,
                aIn.aAliquota,
                aIn.aDescricao
        );
    }

    public static UpdateImpostoCommand from(final String aUuid,
                                            final UpdateImpostoCommand aIn) {

        return new UpdateImpostoCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aNome,
                aIn.aTipoCode,
                aIn.aAliquota,
                aIn.aDescricao
        );
    }
}
