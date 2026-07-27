package com.hvs.webstore.back.app.command.webstore.permissao;

public record DeletePermissaoCommand(Long aId,
                                     String aUuid) {

    public static DeletePermissaoCommand from(final Long aId) {

        return new DeletePermissaoCommand(
                aId,
                null);
    }

    public static DeletePermissaoCommand from(final String aUuid) {

        return new DeletePermissaoCommand(
                null,
                aUuid);
    }
}
