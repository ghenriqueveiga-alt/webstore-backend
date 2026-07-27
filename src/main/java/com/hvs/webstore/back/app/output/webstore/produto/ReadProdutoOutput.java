package com.hvs.webstore.back.app.output.webstore.produto;

import com.hvs.webstore.back.app.output.webstore.caracteristica.ReadCaracteristicaOutput;
import com.hvs.webstore.back.app.output.webstore.imagem.ReadImagemOutput;
import com.hvs.webstore.back.app.output.webstore.video.ReadVideoOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;
import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;
import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;

import java.time.Instant;
import java.util.List;

public record ReadProdutoOutput(Long aId,
                                String aUuid,
                                String aStatusDesc,
                                String aNome,
                                String aDescricao,
                                Preco aPreco,
                                List<ReadCaracteristicaOutput> aCaracteristicas,
                                List<ReadImagemOutput> aImagens,
                                List<ReadVideoOutput> aVideos,
                                Categoria aCategoria,
                                Marca aMarca,
                                Instant aDataPublicacao) {

    public static ReadProdutoOutput from(Produto aProduto) {

        return new ReadProdutoOutput(
                aProduto.getId().getValue(),
                aProduto.getUuid().getValue(),
                aProduto.getStatusCode().getDesc(),
                aProduto.getNome(),
                aProduto.getDescricao(),
                aProduto.getPreco() != null ? aProduto.getPreco() : null,
                aProduto.getCaracteristicas() != null && aProduto.getCaracteristicas().isEmpty() ?
                    aProduto.getCaracteristicas().stream().map(ReadCaracteristicaOutput::from).toList() : null,
                aProduto.getImagens() != null && aProduto.getImagens().isEmpty() ?
                    aProduto.getImagens().stream().map(ReadImagemOutput::from).toList() : null,
                aProduto.getVideos() != null && aProduto.getVideos().isEmpty() ?
                        aProduto.getVideos().stream().map(ReadVideoOutput::from).toList() : null,
                aProduto.getCategoria() != null ? aProduto.getCategoria() : null,
                aProduto.getMarca() != null ? aProduto.getMarca() : null,
                aProduto.getDataPublicacao());
    }

    public static ReadProdutoOutput fromSimple(Produto aProduto) {

        return new ReadProdutoOutput(
                aProduto.getId().getValue(),
                aProduto.getUuid().getValue(),
                null,
                aProduto.getNome(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}