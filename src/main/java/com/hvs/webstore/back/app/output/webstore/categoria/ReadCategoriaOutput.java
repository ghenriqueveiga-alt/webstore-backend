package com.hvs.webstore.back.app.output.webstore.categoria;

import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;

import java.util.List;

public record ReadCategoriaOutput(Long id,
                                  String uuid,
                                  String statusDesc,
                                  String nome,
                                  String descricao,
                                  List<Long> produtoIds) {

    public static ReadCategoriaOutput from(Categoria aCategoria) {

        return new ReadCategoriaOutput(
                aCategoria.getId().getValue(),
                aCategoria.getUuid().getValue(),
                aCategoria.getStatusCode().getDesc(),
                aCategoria.getNome(),
                aCategoria.getDescricao(),
                aCategoria.getProdutos() != null ? aCategoria.getProdutos().stream()
                        .map(p -> p.getId().getValue()).toList() : null);
    }

    public static ReadCategoriaOutput fromSimple(Categoria aCategoria) {

        return new ReadCategoriaOutput(
                aCategoria.getId().getValue(),
                aCategoria.getUuid().getValue(),
                null,
                aCategoria.getNome(),
                null,
                null);
    }
}
