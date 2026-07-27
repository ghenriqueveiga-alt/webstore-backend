package com.hvs.webstore.back.app.command.webstore.endereco;

public record CreateEnderecoCommand(Long aUsuarioId,
                                    String aLogradouro,
                                    String aNumero,
                                    String aComplemento,
                                    String aBairro,
                                    String aCidade,
                                    String aEstado,
                                    String aCep,
                                    Boolean aPrincipal) {

    public static CreateEnderecoCommand from(final Long aUsuarioId,
                                             final String aLogradouro,
                                             final String aNumero,
                                             final String aComplemento,
                                             final String aBairro,
                                             final String aCidade,
                                             final String aEstado,
                                             final String aCep,
                                             final Boolean aPrincipal) {

        return new CreateEnderecoCommand(
                aUsuarioId,
                aLogradouro,
                aNumero,
                aComplemento,
                aBairro,
                aCidade,
                aEstado,
                aCep,
                aPrincipal);
    }
}
