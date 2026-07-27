package com.hvs.webstore.back.app.command.webstore.imposto;

public record DeleteImpostoCommand(Long aId,
                                   String aUuid) {

    public static DeleteImpostoCommand from(final Long aId) {

        return new DeleteImpostoCommand(
                aId,
                null);
    }

    public static DeleteImpostoCommand from(final String aUuid) {

        return new DeleteImpostoCommand(
                null,
                aUuid);
    }
}
