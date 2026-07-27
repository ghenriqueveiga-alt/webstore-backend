package com.hvs.webstore.back.app.output.webstore.boleto;

import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;

public record ReadBoletoOutput(Long aId,
                               String aUuid,
                               String aStatusDesc,
                               String aCodigoBarras,
                               String aVencimento) {

    public static ReadBoletoOutput from(Boleto aBoleto) {

        return new ReadBoletoOutput(
                aBoleto.getId().getValue(),
                aBoleto.getUuid().getValue(),
                aBoleto.getStatusCode().getDesc(),
                aBoleto.getCodigoBarras(),
                aBoleto.getVencimento());
    }

    public static ReadBoletoOutput fromSimple(Boleto aBoleto) {

        return new ReadBoletoOutput(
                aBoleto.getId().getValue(),
                aBoleto.getUuid().getValue(),
                null,
                null,
                null);
    }
}
