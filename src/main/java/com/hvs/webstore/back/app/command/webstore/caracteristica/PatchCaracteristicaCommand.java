package com.hvs.webstore.back.app.command.webstore.caracteristica;

public record PatchCaracteristicaCommand(Long aId,
                                         String aUuid,
                                         String aStatusCode,
                                         String aNome,
                                         String aDescricao) {

    public static PatchCaracteristicaCommand from(final Long aId,
                                                  final PatchCaracteristicaCommand aInput) {

        return new PatchCaracteristicaCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao);
    }

    public static PatchCaracteristicaCommand from(final String aUuid,
                                                  final PatchCaracteristicaCommand aInput) {

        return new PatchCaracteristicaCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao);
    }
}
