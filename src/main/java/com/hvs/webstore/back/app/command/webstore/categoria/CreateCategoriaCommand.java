package com.hvs.webstore.back.app.command.webstore.categoria;

import java.util.List;

public record CreateCategoriaCommand(String aNome,
                                     String aDescricao,
                                     List<Long> aProdutoIds) {

    public static CreateCategoriaCommand from(final String aNome,
                                              final String aDescricao,
                                              final List<Long> aProdutoIds) {

        return new CreateCategoriaCommand(
                aNome,
                aDescricao,
                aProdutoIds);
    }
}
