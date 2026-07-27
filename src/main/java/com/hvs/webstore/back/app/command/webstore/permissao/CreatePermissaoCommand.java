package com.hvs.webstore.back.app.command.webstore.permissao;

public record CreatePermissaoCommand(String aNome,
                                     String aChave,
                                     String aDescricao) {

    public static CreatePermissaoCommand from(final String aNome,
                                              final String aChave,
                                              final String aDescricao) {

        return new CreatePermissaoCommand(
                aNome,
                aChave,
                aDescricao);
    }
}
