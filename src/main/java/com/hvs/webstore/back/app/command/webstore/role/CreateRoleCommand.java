package com.hvs.webstore.back.app.command.webstore.role;

import java.util.List;

public record CreateRoleCommand(String aNome,
                                String aDescricao,
                                List<Long> aPermissaoIds) {

    public static CreateRoleCommand from(final String aNome,
                                         final String aDescricao,
                                         final List<Long> aPermissaoIds) {

        return new CreateRoleCommand(
                aNome,
                aDescricao,
                aPermissaoIds);
    }
}
