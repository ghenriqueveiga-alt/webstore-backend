package com.hvs.webstore.back.app.output.webstore.frete;

import com.hvs.webstore.back.domain.entity.webstore.frete.Frete;

public record CalcularFreteOutput(Long id,
                                  String uuid,
                                  Long valorFrete,
                                  Integer prazoDias,
                                  String tipoFrete,
                                  String message) {

    public static CalcularFreteOutput from(Frete aFrete) {

        return new CalcularFreteOutput(
                aFrete.getId().getValue(),
                aFrete.getUuid().getValue(),
                aFrete.getValorFrete(),
                aFrete.getPrazoDias(),
                aFrete.getTipoFrete().getCode(),
                "The Frete with uuid: " + aFrete.getUuid().getValue() + " has been successfully calculated.");
    }
}
