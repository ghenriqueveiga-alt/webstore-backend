package com.hvs.webstore.back.app.command.webstore.categoria;

import java.util.List;

public record UpdateCategoriaCommand(Long aId,
                                     String aUuid,
                                     String aStatusCode,
                                     String aNome,
                                     String aDescricao,
                                     List<Long> aProdutoIds) {

    public static UpdateCategoriaCommand from(final Long aId,
                                              final UpdateCategoriaCommand aInput) {

        return new UpdateCategoriaCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aProdutoIds
        );
    }

    public static UpdateCategoriaCommand from(final String aUuid,
                                              final UpdateCategoriaCommand aInput) {

        return new UpdateCategoriaCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aProdutoIds
        );
    }
}
