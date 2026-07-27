package com.hvs.webstore.back.app.command.webstore.endereco;

public record UpdateEnderecoCommand(Long aId,
                                    String aUuid,
                                    String aStatusCode,
                                    Long aUsuarioId,
                                    String aLogradouro,
                                    String aNumero,
                                    String aComplemento,
                                    String aBairro,
                                    String aCidade,
                                    String aEstado,
                                    String aCep,
                                    Boolean aPrincipal) {

    public static UpdateEnderecoCommand from(final Long aId,
                                             final UpdateEnderecoCommand aInput) {

        return new UpdateEnderecoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aUsuarioId,
                aInput.aLogradouro,
                aInput.aNumero,
                aInput.aComplemento,
                aInput.aBairro,
                aInput.aCidade,
                aInput.aEstado,
                aInput.aCep,
                aInput.aPrincipal
        );
    }

    public static UpdateEnderecoCommand from(final String aUuid,
                                             final UpdateEnderecoCommand aInput) {

        return new UpdateEnderecoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aUsuarioId,
                aInput.aLogradouro,
                aInput.aNumero,
                aInput.aComplemento,
                aInput.aBairro,
                aInput.aCidade,
                aInput.aEstado,
                aInput.aCep,
                aInput.aPrincipal
        );
    }
}
