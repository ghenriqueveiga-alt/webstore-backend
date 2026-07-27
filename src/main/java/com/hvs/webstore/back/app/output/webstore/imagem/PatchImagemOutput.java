package com.hvs.webstore.back.app.output.webstore.imagem;

import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;

public record PatchImagemOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static PatchImagemOutput from(Imagem aImagem) {

        return new PatchImagemOutput(
                aImagem.getId().getValue(),
                aImagem.getUuid().getValue(),
                "The Image with id: " + aImagem.getUuid().getValue() + " has been successfully patched.");
    }
}
