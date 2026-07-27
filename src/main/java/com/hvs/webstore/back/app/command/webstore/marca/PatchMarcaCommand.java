package com.hvs.webstore.back.app.command.webstore.marca;

import java.util.List;

public record PatchMarcaCommand(Long aId,
                                String aUuid,
                                String aStatusCode,
                                String aNome,
                                String aDescricao,
                                List<Long> aProdutoIds) {

    public static PatchMarcaCommand from(final Long aId,
                                         final PatchMarcaCommand aInput) {

        return new PatchMarcaCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aProdutoIds);
    }

    public static PatchMarcaCommand from(final String aUuid,
                                         final PatchMarcaCommand aInput) {

        return new PatchMarcaCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aProdutoIds);
    }
}
