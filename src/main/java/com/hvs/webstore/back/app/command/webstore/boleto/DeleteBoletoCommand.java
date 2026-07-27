package com.hvs.webstore.back.app.command.webstore.boleto;

public record DeleteBoletoCommand(Long aId,
                                  String aUuid) {

    public static DeleteBoletoCommand from(final Long aId) {

        return new DeleteBoletoCommand(
                aId,
                null);
    }

    public static DeleteBoletoCommand from(final String aUuid) {

        return new DeleteBoletoCommand(
                null,
                aUuid);
    }
}
