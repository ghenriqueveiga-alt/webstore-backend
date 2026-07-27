package com.hvs.webstore.back.app.command.webstore.endereco;

public record PatchEnderecoCommand(Long aId,
                                   String aUuid,
                                   String aStatusCode,
                                   String aLogradouro,
                                   String aNumero,
                                   String aComplemento,
                                   String aBairro,
                                   String aCidade,
                                   String aEstado,
                                   String aCep,
                                   Boolean aPrincipal) {

    public static PatchEnderecoCommand from(final Long aId,
                                            final PatchEnderecoCommand aInput) {

        return new PatchEnderecoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aLogradouro,
                aInput.aNumero,
                aInput.aComplemento,
                aInput.aBairro,
                aInput.aCidade,
                aInput.aEstado,
                aInput.aCep,
                aInput.aPrincipal);
    }

    public static PatchEnderecoCommand from(final String aUuid,
                                            final PatchEnderecoCommand aInput) {

        return new PatchEnderecoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aLogradouro,
                aInput.aNumero,
                aInput.aComplemento,
                aInput.aBairro,
                aInput.aCidade,
                aInput.aEstado,
                aInput.aCep,
                aInput.aPrincipal);
    }
}
