package com.hvs.webstore.back.app.output.webstore.imagem;

import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;

public record DeleteImagemOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static DeleteImagemOutput from(Imagem aImagem) {

        return new DeleteImagemOutput(
                aImagem.getId().getValue(),
                aImagem.getUuid().getValue(),
                "The Image with id: " + aImagem.getUuid().getValue() + " has been successfully deleted.");
    }
}
