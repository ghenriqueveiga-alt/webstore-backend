package com.hvs.webstore.back.app.command.webstore.categoria;

import java.util.List;

public record PatchCategoriaCommand(Long aId,
                                    String aUuid,
                                    String aStatusCode,
                                    String aNome,
                                    String aDescricao,
                                    List<Long> aProdutoIds) {

    public static PatchCategoriaCommand from(final Long aId,
                                             final PatchCategoriaCommand aInput) {

        return new PatchCategoriaCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aProdutoIds);
    }

    public static PatchCategoriaCommand from(final String aUuid,
                                             final PatchCategoriaCommand aInput) {

        return new PatchCategoriaCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aProdutoIds);
    }
}
