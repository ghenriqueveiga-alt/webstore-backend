package com.hvs.webstore.back.app.command.television.canal;

public record UpdateCanalCommand(Long aId,
                                 String aUuid,
                                 String aStatusDesc,
                                 String aNome,
                                 String aDescricao,
                                 String aLogotipoUrl,
                                 String aSite) {

    public static UpdateCanalCommand from(final Long aId,
                                          final UpdateCanalCommand aInput) {

        return new UpdateCanalCommand(
                aId,
                null,
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aLogotipoUrl,
                aInput.aSite);
    }

    public static UpdateCanalCommand from(final String aUuid,
                                          final UpdateCanalCommand aInput) {

        return new UpdateCanalCommand(
                null,
                aUuid,
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aLogotipoUrl,
                aInput.aSite);
    }
}
