package com.hvs.webstore.back.app.command.webstore.permissao;

public record UpdatePermissaoCommand(Long aId,
                                     String aUuid,
                                     String aStatusCode,
                                     String aNome,
                                     String aChave,
                                     String aDescricao) {

    public static UpdatePermissaoCommand from(final Long aId,
                                              final UpdatePermissaoCommand aInput) {

        return new UpdatePermissaoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aChave,
                aInput.aDescricao
        );
    }

    public static UpdatePermissaoCommand from(final String aUuid,
                                              final UpdatePermissaoCommand aInput) {

        return new UpdatePermissaoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aChave,
                aInput.aDescricao
        );
    }
}
