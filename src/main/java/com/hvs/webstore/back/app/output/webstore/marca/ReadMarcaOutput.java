package com.hvs.webstore.back.app.output.webstore.marca;

import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;

import java.util.List;

public record ReadMarcaOutput(Long id,
                              String uuid,
                              String statusDesc,
                              String nome,
                              String descricao,
                              List<Long> produtoIds) {

    public static ReadMarcaOutput from(Marca aMarca) {

        return new ReadMarcaOutput(
                aMarca.getId().getValue(),
                aMarca.getUuid().getValue(),
                aMarca.getStatusCode().getDesc(),
                aMarca.getNome(),
                aMarca.getDescricao(),
                aMarca.getProdutos() != null ? aMarca.getProdutos().stream()
                        .map(p -> p.getId().getValue()).toList() : null);
    }

    public static ReadMarcaOutput fromSimple(Marca aMarca) {

        return new ReadMarcaOutput(
                aMarca.getId().getValue(),
                aMarca.getUuid().getValue(),
                null,
                aMarca.getNome(),
                null,
                null);
    }
}
