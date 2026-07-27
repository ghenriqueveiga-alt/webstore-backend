package com.hvs.webstore.back.app.command.webstore.boleto;

public record ReadBoletoCommand(Long aId,
                                String aUuid) {

    public static ReadBoletoCommand from(final Long aId) {

        return new ReadBoletoCommand(
                aId,
                null
        );
    }

    public static ReadBoletoCommand from(final String aUuid) {

        return new ReadBoletoCommand(
                null,
                aUuid
        );
    }
}
