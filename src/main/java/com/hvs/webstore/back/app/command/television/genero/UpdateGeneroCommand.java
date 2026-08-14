package com.hvs.webstore.back.app.command.television.genero;

public record UpdateGeneroCommand(Long aId,
                                  String aUuid,
                                  String aStatusDesc,
                                  String aNome,
                                  String aDescricao) {

    public static UpdateGeneroCommand from(final Long aId,
                                           final UpdateGeneroCommand aInput) {

        return new UpdateGeneroCommand(
                aId,
                null,
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aDescricao);
    }

    public static UpdateGeneroCommand from(final String aUuid,
                                           final UpdateGeneroCommand aInput) {

        return new UpdateGeneroCommand(
                null,
                aUuid,
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aDescricao);
    }
}
