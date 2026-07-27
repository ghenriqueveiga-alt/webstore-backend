package com.hvs.webstore.back.app.output.webstore.produto;

import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;

import java.util.List;

public record ReadAllProdutoOutput(List<ReadProdutoOutput> aProdutos) {

    public static ReadAllProdutoOutput from(List<Produto> aProduto) {

        return new ReadAllProdutoOutput(aProduto.stream().map(ReadProdutoOutput::from).toList());
    }
}
