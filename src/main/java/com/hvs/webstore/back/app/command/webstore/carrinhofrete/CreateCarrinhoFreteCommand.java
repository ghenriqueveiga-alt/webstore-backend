package com.hvs.webstore.back.app.command.webstore.carrinhofrete;

public record CreateCarrinhoFreteCommand(Long aCarrinhoId,
                                         Long aFreteId,
                                         Long aValor,
                                         Integer aPrazo,
                                         Long aTransportadoraId) {

    public static CreateCarrinhoFreteCommand from(final Long aCarrinhoId,
                                                  final Long aFreteId,
                                                  final Long aValor,
                                                  final Integer aPrazo,
                                                  final Long aTransportadoraId) {

        return new CreateCarrinhoFreteCommand(
                aCarrinhoId,
                aFreteId,
                aValor,
                aPrazo,
                aTransportadoraId);
    }
}
