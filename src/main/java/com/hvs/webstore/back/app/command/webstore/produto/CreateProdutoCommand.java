package com.hvs.webstore.back.app.command.webstore.produto;

import java.util.List;

public record CreateProdutoCommand(String aNome,
                                   String aDescricao,
                                   Long aPrecoId,
                                   List<Long> aCaracteristicaIds,
                                   List<Long> aImagenIds,
                                   List<Long> aVideoIds,
                                   Long aCategoriaId,
                                   Long aMarcaId) {

    public static CreateProdutoCommand from(final String aNome,
                                            final String aDescricao,
                                            final Long aPrecoId,
                                            final List<Long> aCaracteristicaIds,
                                            final List<Long> aImagenIds,
                                            final List<Long> aVideoIds,
                                            final Long aCategoriaId,
                                            final Long aMarcaId) {

        return new CreateProdutoCommand(
                aNome,
                aDescricao,
                aPrecoId,
                aCaracteristicaIds,
                aImagenIds,
                aVideoIds,
                aCategoriaId,
                aMarcaId);
    }
}
