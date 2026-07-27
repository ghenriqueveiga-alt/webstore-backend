package com.hvs.webstore.back.app.output.webstore.cartao;

import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;

public record UpdateCartaoOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static UpdateCartaoOutput from(Cartao aCartao) {

        return new UpdateCartaoOutput(
                aCartao.getId().getValue(),
                aCartao.getUuid().getValue(),
                "The Cartao with id: " + aCartao.getUuid().getValue() + " has been successfully updated.");
    }
}
