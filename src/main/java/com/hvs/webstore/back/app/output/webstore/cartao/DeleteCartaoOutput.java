package com.hvs.webstore.back.app.output.webstore.cartao;

import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;

public record DeleteCartaoOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static DeleteCartaoOutput from(Cartao aCartao) {

        return new DeleteCartaoOutput(
                aCartao.getId().getValue(),
                aCartao.getUuid().getValue(),
                "The Cartao with id: " + aCartao.getUuid().getValue() + " has been successfully deleted.");
    }
}
