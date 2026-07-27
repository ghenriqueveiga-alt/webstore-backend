package com.hvs.webstore.back.app.command.webstore.carrinho;

public record CreateCarrinhoCommand(Long aUsuarioId) {

    public static CreateCarrinhoCommand from(final Long aUsuarioId) {

        return new CreateCarrinhoCommand(aUsuarioId);
    }
}
