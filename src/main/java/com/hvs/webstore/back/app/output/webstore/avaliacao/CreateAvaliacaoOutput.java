package com.hvs.webstore.back.app.output.webstore.avaliacao;

import com.hvs.webstore.back.domain.entity.webstore.avaliacao.Avaliacao;

public record CreateAvaliacaoOutput(Long aId,
                                    String aUuid,
                                    String aMessage) {

    public static CreateAvaliacaoOutput from(Avaliacao aAvaliacao) {

        return new CreateAvaliacaoOutput(
                aAvaliacao.getId().getValue(),
                aAvaliacao.getUuid().getValue(),
                "The Avaliacao with id: " + aAvaliacao.getUuid().getValue() + " has been successfully created.");
    }
}
