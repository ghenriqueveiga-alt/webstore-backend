package com.hvs.webstore.back.app.command.webstore.caracteristica;

public record UpdateCaracteristicaCommand(Long aId,
                                          String aUuid,
                                          String aStatusCode,
                                          String aNome,
                                          String aDescricao) {

    public static UpdateCaracteristicaCommand from(final Long aId,
                                                   final UpdateCaracteristicaCommand aInput) {

        return new UpdateCaracteristicaCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao
        );
    }

    public static UpdateCaracteristicaCommand from(final String aUuid,
                                                   final UpdateCaracteristicaCommand aInput) {

        return new UpdateCaracteristicaCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao
        );
    }
}
