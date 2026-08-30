package com.hvs.webstore.back.app.command.television.genero;

public record PatchGeneroCommand(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 String aNome,
                                 String aDescricao) {

    public static PatchGeneroCommand from(final Long aId,
                                          final PatchGeneroCommand aInput) {

        return new PatchGeneroCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao);
    }

    public static PatchGeneroCommand from(final String aUuid,
                                          final PatchGeneroCommand aInput) {

        return new PatchGeneroCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao);
    }
}
