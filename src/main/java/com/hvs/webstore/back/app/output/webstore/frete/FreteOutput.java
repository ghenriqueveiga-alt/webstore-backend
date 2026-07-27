package com.hvs.webstore.back.app.output.webstore.frete;

import com.hvs.webstore.back.domain.entity.webstore.frete.Frete;

import java.time.Instant;

public record FreteOutput(Long id,
                          String uuid,
                          String status,
                          String cepOrigem,
                          String cepDestino,
                          String tipoFrete,
                          Double peso,
                          Double comprimento,
                          Double largura,
                          Double altura,
                          Long valorFrete,
                          Integer prazoDias,
                          Instant criadoEm) {

    public static FreteOutput from(Frete aFrete) {

        return new FreteOutput(
                aFrete.getId().getValue(),
                aFrete.getUuid().getValue(),
                aFrete.getStatusCode().getDesc(),
                aFrete.getCepOrigem(),
                aFrete.getCepDestino(),
                aFrete.getTipoFrete().getDesc(),
                aFrete.getPeso(),
                aFrete.getComprimento(),
                aFrete.getLargura(),
                aFrete.getAltura(),
                aFrete.getValorFrete(),
                aFrete.getPrazoDias(),
                aFrete.getCriadoEm());
    }
}
