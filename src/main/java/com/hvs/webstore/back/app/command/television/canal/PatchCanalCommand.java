package com.hvs.webstore.back.app.command.television.canal;

public record PatchCanalCommand(Long aId,
                                String aUuid,
                                String aStatusCode,
                                String aNome,
                                String aDescricao,
                                String aLogotipoUrl,
                                String aSite) {

    public static PatchCanalCommand from(final Long aId,
                                         final PatchCanalCommand aInput) {

        return new PatchCanalCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aLogotipoUrl,
                aInput.aSite);
    }

    public static PatchCanalCommand from(final String aUuid,
                                         final PatchCanalCommand aInput) {

        return new PatchCanalCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aLogotipoUrl,
                aInput.aSite);
    }
}
