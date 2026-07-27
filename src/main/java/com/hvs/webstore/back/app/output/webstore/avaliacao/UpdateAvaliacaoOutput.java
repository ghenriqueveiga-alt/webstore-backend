package com.hvs.webstore.back.app.output.webstore.avaliacao;

import com.hvs.webstore.back.domain.entity.webstore.avaliacao.Avaliacao;

public record UpdateAvaliacaoOutput(Long aId,
                                    String aUuid,
                                    String aMessage) {

    public static UpdateAvaliacaoOutput from(Avaliacao aAvaliacao) {

        return new UpdateAvaliacaoOutput(
                aAvaliacao.getId().getValue(),
                aAvaliacao.getUuid().getValue(),
                "The Avaliacao with id: " + aAvaliacao.getUuid().getValue() + " has been successfully updated.");
    }
}
