package com.hvs.webstore.back.app.command.webstore.usuario;

public record CreateUsuarioCommand(String aNome,
                                   String aEmail,
                                   String aSenha,
                                   String aTelefone) {

    public static CreateUsuarioCommand from(final String aNome,
                                            final String aEmail,
                                            final String aSenha,
                                            final String aTelefone) {

        return new CreateUsuarioCommand(
                aNome,
                aEmail,
                aSenha,
                aTelefone);
    }
}
