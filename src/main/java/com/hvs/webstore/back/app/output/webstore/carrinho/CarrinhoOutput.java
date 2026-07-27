package com.hvs.webstore.back.app.output.webstore.carrinho;

import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.ItemCarrinho;

import java.time.Instant;
import java.util.List;

public record CarrinhoOutput(Long id,
                             String uuid,
                             String status,
                             Long usuarioId,
                             List<ItemCarrinhoOutput> items,
                             Instant criadoEm,
                             Instant atualizadoEm) {

    public static CarrinhoOutput from(Carrinho aCarrinho) {

        return new CarrinhoOutput(
                aCarrinho.getId().getValue(),
                aCarrinho.getUuid().getValue(),
                aCarrinho.getStatusCode().getCode(),
                aCarrinho.getUsuario() != null ? aCarrinho.getUsuario().getId().getValue() : null,
                aCarrinho.getItems().stream().map(ItemCarrinhoOutput::from).toList(),
                aCarrinho.getCriadoEm(),
                aCarrinho.getAtualizadoEm());
    }

    public record ItemCarrinhoOutput(String uuid,
                                     Long produtoId,
                                     Integer quantidade,
                                     Long precoId) {

        public static ItemCarrinhoOutput from(ItemCarrinho aItemCarrinho) {

            return new ItemCarrinhoOutput(
                    aItemCarrinho.getUuid().getValue(),
                    aItemCarrinho.getProduto() != null ? aItemCarrinho.getProduto().getId().getValue() : null,
                    aItemCarrinho.getQuantidade(),
                    null);
        }
    }
}
