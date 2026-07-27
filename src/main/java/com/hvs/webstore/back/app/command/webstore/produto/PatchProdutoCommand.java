package com.hvs.webstore.back.app.command.webstore.produto;

import java.util.List;

public record PatchProdutoCommand(Long aId,
                                  String aUuid,
                                  String aStatusCode,
                                  String aNome,
                                  String aDescricao,
                                  Long aPrecoId,
                                  Long aMarcaId,
                                  List<Long> aCaracteristicaIds,
                                  List<Long> aImagemIds,
                                  List<Long> aVideoIds,
                                  Long aCategoriaId) {

    public static PatchProdutoCommand from(final Long aId,
                                           final PatchProdutoCommand aInput) {

        return new PatchProdutoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aPrecoId,
                aInput.aMarcaId,
                aInput.aCaracteristicaIds,
                aInput.aImagemIds,
                aInput.aVideoIds,
                aInput.aCategoriaId);
    }

    public static PatchProdutoCommand from(final String aUuid,
                                           final PatchProdutoCommand aInput) {

        return new PatchProdutoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aPrecoId,
                aInput.aMarcaId,
                aInput.aCaracteristicaIds,
                aInput.aImagemIds,
                aInput.aVideoIds,
                aInput.aCategoriaId);
    }
}
