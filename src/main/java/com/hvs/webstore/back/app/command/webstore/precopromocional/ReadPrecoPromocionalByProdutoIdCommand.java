package com.hvs.webstore.back.app.command.webstore.precopromocional;

public record ReadPrecoPromocionalByProdutoIdCommand(Long aProdutoId) {

    public static ReadPrecoPromocionalByProdutoIdCommand from(final Long aProdutoId) {

        return new ReadPrecoPromocionalByProdutoIdCommand(aProdutoId);
    }
}
