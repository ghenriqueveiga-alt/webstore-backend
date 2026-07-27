package com.hvs.webstore.back.app.command.webstore.usuario;

import java.time.Instant;

public record UpdateUsuarioCommand(Long aId,
                                   String aUuid,
                                   String aStatusCode,
                                   String aNome,
                                   String aEmail,
                                   String aSenha,
                                   String aTelefone,
                                   Instant aDataCadastro) {

    public static UpdateUsuarioCommand from(final Long aId,
                                            final UpdateUsuarioCommand aInput) {

        return new UpdateUsuarioCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aEmail,
                aInput.aSenha,
                aInput.aTelefone,
                aInput.aDataCadastro
        );
    }

    public static UpdateUsuarioCommand from(final String aUuid,
                                            final UpdateUsuarioCommand aInput) {

        return new UpdateUsuarioCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aEmail,
                aInput.aSenha,
                aInput.aTelefone,
                aInput.aDataCadastro
        );
    }
}
