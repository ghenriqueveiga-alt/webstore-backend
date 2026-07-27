package com.hvs.webstore.back.app.command.webstore.role;

import java.util.List;

public record UpdateRoleCommand(Long aId,
                                String aUuid,
                                String aStatusCode,
                                String aNome,
                                String aDescricao,
                                List<Long> aPermissaoIds) {

    public static UpdateRoleCommand from(final Long aId,
                                         final UpdateRoleCommand aIn) {

        return new UpdateRoleCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aNome,
                aIn.aDescricao,
                aIn.aPermissaoIds
        );
    }

    public static UpdateRoleCommand from(final String aUuid,
                                         final UpdateRoleCommand aIn) {

        return new UpdateRoleCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aNome,
                aIn.aDescricao,
                aIn.aPermissaoIds
        );
    }
}
