package com.hvs.webstore.back.app.command.television.canal;

public record PatchCanalCommand(Long aId,
                                String aUuid,
                                String aStatusDesc,
                                String aNome,
                                String aDescricao,
                                String aLogotipoUrl,
                                String aSite) {

    public static PatchCanalCommand from(final Long aId,
                                         final PatchCanalCommand aInput) {

        return new PatchCanalCommand(
                aId,
                null,
                aInput.aStatusDesc,
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
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aLogotipoUrl,
                aInput.aSite);
    }
}
