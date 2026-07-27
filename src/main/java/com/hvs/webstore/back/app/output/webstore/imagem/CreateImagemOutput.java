package com.hvs.webstore.back.app.output.webstore.imagem;

import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;

public record CreateImagemOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static CreateImagemOutput from(Imagem aImagem) {

        return new CreateImagemOutput(
                aImagem.getId().getValue(),
                aImagem.getUuid().getValue(),
                "The Image with id: " + aImagem.getUuid().getValue() + " has been successfully created.");
    }
}
