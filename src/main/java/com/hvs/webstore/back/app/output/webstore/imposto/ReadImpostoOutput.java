package com.hvs.webstore.back.app.output.webstore.imposto;

import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;

public record ReadImpostoOutput(Long aId,
                                String aUuid,
                                String aStatusDesc,
                                String aNome,
                                String aTipoCode,
                                Integer aAliquota,
                                String aDescricao) {

    public static ReadImpostoOutput from(Imposto aImposto) {

        return new ReadImpostoOutput(
                aImposto.getId().getValue(),
                aImposto.getUuid().getValue(),
                aImposto.getStatusCode().getDesc(),
                aImposto.getNome(),
                aImposto.getTipoImposto().getCode(),
                aImposto.getAliquota(),
                aImposto.getDescricao());
    }

    public static ReadImpostoOutput fromSimple(Imposto aImposto) {

        return new ReadImpostoOutput(
                aImposto.getId().getValue(),
                aImposto.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null);
    }
}
