package com.hvs.webstore.back.app.output.webstore.estoque;

import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;

public record EstoqueOutput(Long id,
                            String uuid,
                            String status,
                            Long produtoId,
                            Integer quantidade,
                            Integer reservado,
                            Integer quantidadeMinima) {

    public static EstoqueOutput from(Estoque aEstoque) {

        return new EstoqueOutput(
                aEstoque.getId().getValue(),
                aEstoque.getUuid().getValue(),
                aEstoque.getStatusCode().getDesc(),
                aEstoque.getProduto().getId().getValue(),
                aEstoque.getQuantidade(),
                aEstoque.getReservado(),
                aEstoque.getQuantidadeMinima());
    }

    public static EstoqueOutput fromSimple(Estoque aEstoque) {

        return new EstoqueOutput(
                aEstoque.getId().getValue(),
                aEstoque.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null);
    }
}
