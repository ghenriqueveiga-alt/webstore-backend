package com.hvs.webstore.back.app.output.webstore.imagem;

import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;

public record UpdateImagemOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static UpdateImagemOutput from(Imagem aImagem) {

        return new UpdateImagemOutput(
                aImagem.getId().getValue(),
                aImagem.getUuid().getValue(),
                "The Image with id: " + aImagem.getUuid().getValue() + " has been successfully updated.");
    }
}
