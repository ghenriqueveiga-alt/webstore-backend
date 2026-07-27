package com.hvs.webstore.back.app.command.webstore.produto;

import java.time.Instant;
import java.util.List;

public record UpdateProdutoCommand(Long aId,
                                   String aUuid,
                                   String aStatusCode,
                                   String aNome,
                                   String aDescricao,
                                   Long aPrecoId,
                                   List<Long> aCaracteristicaIds,
                                   List<Long> aImagemIds,
                                   List<Long> aVideoIds,
                                   Long aCategoriaId,
                                   Long aMarcaId,
                                   Instant aDataPublicacao) {

    public static UpdateProdutoCommand from(final Long aId,
                                            final String aUuid,
                                            final String aStatusCode,
                                            final String aNome,
                                            final String aDescricao,
                                            final Long aPrecoId,
                                            final List<Long> aCaracteristicaIds,
                                            final List<Long> aImagemIds,
                                            final List<Long> aVideoIds,
                                            final Long aCategoriaId,
                                            final Long aMarcaId,
                                            final Instant aDataPublicacao) {

        return new UpdateProdutoCommand(
                aId,
                aUuid,
                aStatusCode,
                aNome,
                aDescricao,
                aPrecoId,
                aCaracteristicaIds,
                aImagemIds,
                aVideoIds,
                aCategoriaId,
                aMarcaId,
                aDataPublicacao
        );
    }

    public static UpdateProdutoCommand from(final Long aId,
                                            final UpdateProdutoCommand aInput) {

        return new UpdateProdutoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aPrecoId,
                aInput.aCaracteristicaIds,
                aInput.aImagemIds,
                aInput.aVideoIds,
                aInput.aCategoriaId,
                aInput.aMarcaId,
                aInput.aDataPublicacao
        );
    }

    public static UpdateProdutoCommand from(final String aUuid,
                                            final UpdateProdutoCommand aInput) {

        return new UpdateProdutoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aPrecoId,
                aInput.aCaracteristicaIds,
                aInput.aImagemIds,
                aInput.aVideoIds,
                aInput.aCategoriaId,
                aInput.aMarcaId,
                aInput.aDataPublicacao
        );
    }
}
