package com.hvs.webstore.back.app.command.webstore.usuario;

import java.time.Instant;

public record PatchUsuarioCommand(Long aId,
                                  String aUuid,
                                  String aStatusCode,
                                  String aNome,
                                  String aEmail,
                                  String aSenha,
                                  String aTelefone,
                                  Instant aDataCadastro) {

    public static PatchUsuarioCommand from(final Long aId,
                                           final PatchUsuarioCommand aInput) {

        return new PatchUsuarioCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aEmail,
                aInput.aSenha,
                aInput.aTelefone,
                aInput.aDataCadastro);
    }

    public static PatchUsuarioCommand from(final String aUuid,
                                           final PatchUsuarioCommand aInput) {

        return new PatchUsuarioCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aEmail,
                aInput.aSenha,
                aInput.aTelefone,
                aInput.aDataCadastro);
    }
}
