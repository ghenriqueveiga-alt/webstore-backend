package com.hvs.webstore.back.app.command.webstore.permissao;

public record ReadPermissaoCommand(Long aId,
                                   String aUuid) {

    public static ReadPermissaoCommand from(final Long aId) {

        return new ReadPermissaoCommand(
                aId,
                null
        );
    }

    public static ReadPermissaoCommand from(final String aUuid) {

        return new ReadPermissaoCommand(
                null,
                aUuid
        );
    }
}
