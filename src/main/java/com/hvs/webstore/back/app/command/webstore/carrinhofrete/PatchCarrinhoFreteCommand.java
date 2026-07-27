package com.hvs.webstore.back.app.command.webstore.carrinhofrete;

public record PatchCarrinhoFreteCommand(String aUuid,
                                        String aStatusCode,
                                        Long aCarrinhoId,
                                        Long aFreteId,
                                        Long aValor,
                                        Integer aPrazo,
                                        Long aTransportadoraId) {

    public static PatchCarrinhoFreteCommand from(final String aUuid,
                                                 final String aStatusCode,
                                                 final Long aCarrinhoId,
                                                 final Long aFreteId,
                                                 final Long aValor,
                                                 final Integer aPrazo,
                                                 final Long aTransportadoraId) {

        return new PatchCarrinhoFreteCommand(
                aUuid,
                aStatusCode,
                aCarrinhoId,
                aFreteId,
                aValor,
                aPrazo,
                aTransportadoraId);
    }
}
