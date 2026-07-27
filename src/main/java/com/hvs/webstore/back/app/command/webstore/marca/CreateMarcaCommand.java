package com.hvs.webstore.back.app.command.webstore.marca;

import java.util.List;

public record CreateMarcaCommand(String aNome,
                                 String aDescricao,
                                 List<Long> aProdutoIds) {

    public static CreateMarcaCommand from(final String aNome,
                                          final String aDescricao,
                                          final List<Long> aProdutoIds) {

        return new CreateMarcaCommand(
                aNome,
                aDescricao,
                aProdutoIds);
    }
}
