package com.hvs.webstore.back.app.command.webstore.imposto;

public record ReadImpostoCommand(Long aId,
                                 String aUuid) {

    public static ReadImpostoCommand from(final Long aId) {

        return new ReadImpostoCommand(
                aId,
                null
        );
    }

    public static ReadImpostoCommand from(final String aUuid) {

        return new ReadImpostoCommand(
                null,
                aUuid
        );
    }
}
