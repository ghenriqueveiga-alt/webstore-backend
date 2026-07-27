package com.hvs.webstore.back.app.output.webstore.boleto;

import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;

public record UpdateBoletoOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static UpdateBoletoOutput from(Boleto aBoleto) {

        return new UpdateBoletoOutput(
                aBoleto.getId().getValue(),
                aBoleto.getUuid().getValue(),
                "The Boleto with id: " + aBoleto.getUuid().getValue() + " has been successfully updated.");
    }
}
