package com.hvs.webstore.back.app.command.webstore.cupom;

public record ValidarCupomCommand(String codigo,
                                  Long valorPedido) {

    public static ValidarCupomCommand from(final String codigo,
                                           final Long valorPedido) {

        return new ValidarCupomCommand(
                codigo,
                valorPedido);
    }
}
