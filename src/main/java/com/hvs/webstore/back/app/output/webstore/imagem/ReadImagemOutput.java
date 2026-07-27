package com.hvs.webstore.back.app.output.webstore.imagem;

import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;

public record ReadImagemOutput(Long aId,
                               String aUuid,
                               String aStatusDesc,
                               String aNome,
                               String aCaminho,
                               String aExtensao,
                               String aTamanho,
                               String aResolucao) {

    public static ReadImagemOutput from(Imagem aImagem) {

        return new ReadImagemOutput(
                aImagem.getId().getValue(),
                aImagem.getUuid().getValue(),
                aImagem.getStatusCode().getDesc(),
                aImagem.getNome(),
                aImagem.getCaminho(),
                aImagem.getExtensao(),
                aImagem.getTamanho(),
                aImagem.getResolucao());
    }

    public static ReadImagemOutput fromSimple(Imagem aImagem) {

        return new ReadImagemOutput(
                aImagem.getId().getValue(),
                aImagem.getUuid().getValue(),
                null,
                aImagem.getNome(),
                null,
                null,
                null,
                null);
    }
}
