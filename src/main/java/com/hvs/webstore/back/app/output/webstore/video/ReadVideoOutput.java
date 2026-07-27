package com.hvs.webstore.back.app.output.webstore.video;

import com.hvs.webstore.back.domain.entity.webstore.video.Video;

public record ReadVideoOutput(Long aId,
                              String aUuid,
                              String aStatusDesc,
                              String aNome,
                              String aCaminho,
                              String aExtensao,
                              String aTamanho,
                              String aDuracao,
                              String aResolucao,
                              Long aProdutoId) {

    public static ReadVideoOutput from(Video aVideo) {

        return new ReadVideoOutput(
                aVideo.getId().getValue(),
                aVideo.getUuid().getValue(),
                aVideo.getStatusCode().getDesc(),
                aVideo.getNome(),
                aVideo.getCaminho(),
                aVideo.getExtensao(),
                aVideo.getTamanho(),
                aVideo.getDuracao(),
                aVideo.getResolucao(),
                aVideo.getProduto() != null ? aVideo.getProduto().getId().getValue() : null);
    }

    public static ReadVideoOutput fromSimple(Video aVideo) {

        return new ReadVideoOutput(
                aVideo.getId().getValue(),
                aVideo.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
