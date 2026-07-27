package com.hvs.webstore.back.app.output.webstore.avaliacao;

import com.hvs.webstore.back.domain.entity.webstore.avaliacao.Avaliacao;

import java.time.Instant;

public record AvaliacaoOutput(Long id,
                              String uuid,
                              String status,
                              Long produtoId,
                              Long usuarioId,
                              Integer nota,
                              String titulo,
                              String comentario,
                              Boolean verificada,
                              Instant criadoEm,
                              Instant atualizadoEm) {

    public static AvaliacaoOutput from(Avaliacao aAvaliacao) {

        return new AvaliacaoOutput(
                aAvaliacao.getId().getValue(),
                aAvaliacao.getUuid().getValue(),
                aAvaliacao.getStatusCode().getDesc(),
                aAvaliacao.getProduto() != null ? aAvaliacao.getProduto().getId().getValue() : null,
                aAvaliacao.getUsuario() != null ? aAvaliacao.getUsuario().getId().getValue() : null,
                aAvaliacao.getNota(),
                aAvaliacao.getTitulo(),
                aAvaliacao.getComentario(),
                aAvaliacao.getVerificada(),
                aAvaliacao.getCriadoEm(),
                aAvaliacao.getAtualizadoEm());
    }

    public static AvaliacaoOutput fromSimple(Avaliacao aAvaliacao) {

        return new AvaliacaoOutput(
                aAvaliacao.getId().getValue(),
                aAvaliacao.getUuid().getValue(),
                null,
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
