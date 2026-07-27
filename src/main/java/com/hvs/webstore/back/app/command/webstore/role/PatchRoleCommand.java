package com.hvs.webstore.back.app.command.webstore.role;

import java.util.List;

public record PatchRoleCommand(Long aId,
                               String aUuid,
                               String aStatusCode,
                               String aNome,
                               String aDescricao,
                               List<Long> aPermissaoIds) {

    public static PatchRoleCommand from(final Long aId,
                                        final PatchRoleCommand aIn) {

        return new PatchRoleCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aNome,
                aIn.aDescricao,
                aIn.aPermissaoIds);
    }

    public static PatchRoleCommand from(final String aUuid,
                                        final PatchRoleCommand aIn) {

        return new PatchRoleCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aNome,
                aIn.aDescricao,
                aIn.aPermissaoIds);
    }
}
