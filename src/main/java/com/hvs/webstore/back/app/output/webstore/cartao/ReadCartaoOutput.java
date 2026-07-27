package com.hvs.webstore.back.app.output.webstore.cartao;

import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;

public record ReadCartaoOutput(Long aId,
                               String aUuid,
                               String aStatusDesc,
                               String aNomeTitular,
                               String aNumero,
                               String aBandeira,
                               String aTipo,
                               Integer aMesVencimento,
                               Integer aAnoVencimento,
                               String aCvv) {

    public static ReadCartaoOutput from(Cartao aCartao) {

        return new ReadCartaoOutput(
                aCartao.getId().getValue(),
                aCartao.getUuid().getValue(),
                aCartao.getStatusCode().getDesc(),
                aCartao.getNomeTitular(),
                aCartao.getNumero(),
                aCartao.getBandeira(),
                aCartao.getTipo(),
                aCartao.getMesVencimento(),
                aCartao.getAnoVencimento(),
                aCartao.getCvv());
    }

    public static ReadCartaoOutput fromSimple(Cartao aCartao) {

        return new ReadCartaoOutput(
                aCartao.getId().getValue(),
                aCartao.getUuid().getValue(),
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
