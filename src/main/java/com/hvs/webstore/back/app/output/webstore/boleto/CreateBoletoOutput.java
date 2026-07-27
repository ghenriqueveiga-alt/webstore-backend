package com.hvs.webstore.back.app.output.webstore.boleto;

import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;

public record CreateBoletoOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static CreateBoletoOutput from(Boleto aBoleto) {

        return new CreateBoletoOutput(
                aBoleto.getId().getValue(),
                aBoleto.getUuid().getValue(),
                "The Boleto with id: " + aBoleto.getUuid().getValue() + " has been successfully created.");
    }
}
