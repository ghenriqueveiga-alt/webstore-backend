package com.hvs.webstore.back.app.command.webstore.marca;

import java.util.List;

public record UpdateMarcaCommand(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 String aNome,
                                 String aDescricao,
                                 List<Long> aProdutoIds) {

    public static UpdateMarcaCommand from(final Long aId,
                                          final UpdateMarcaCommand aInput) {

        return new UpdateMarcaCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aProdutoIds
        );
    }

    public static UpdateMarcaCommand from(final String aUuid,
                                          final UpdateMarcaCommand aInput) {

        return new UpdateMarcaCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aProdutoIds
        );
    }
}
