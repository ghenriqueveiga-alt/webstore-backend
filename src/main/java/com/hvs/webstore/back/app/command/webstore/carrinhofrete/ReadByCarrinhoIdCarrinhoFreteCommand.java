package com.hvs.webstore.back.app.command.webstore.carrinhofrete;

public record ReadByCarrinhoIdCarrinhoFreteCommand(Long aCarrinhoId) {

    public static ReadByCarrinhoIdCarrinhoFreteCommand from(final Long aCarrinhoId) {

        return new ReadByCarrinhoIdCarrinhoFreteCommand(aCarrinhoId);
    }
}
