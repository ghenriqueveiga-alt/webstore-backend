package com.hvs.webstore.back.app.output.television.arquivo;

import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;

public record ReadArquivoOutput(Long aId,
                                String aUuid,
                                String aStatusCode,
                                String aNome,
                                String aTipo,
                                Long aTamanho,
                                String aCaminho,
                                String aDuracao) {

    public static ReadArquivoOutput from(final Arquivo aArquivo) {

        return new ReadArquivoOutput(
                aArquivo.getId().getValue(),
                aArquivo.getUuid().getValue(),
                aArquivo.getStatus().getCode(),
                aArquivo.getNome(),
                aArquivo.getTipo(),
                aArquivo.getTamanho(),
                aArquivo.getCaminho(),
                aArquivo.getDuracao());
    }

    public static ReadArquivoOutput fromSimple(final Arquivo aArquivo) {

        return new ReadArquivoOutput(
                aArquivo.getId().getValue(),
                aArquivo.getUuid().getValue(),
                aArquivo.getStatus().getCode(),
                null,
                null,
                null,
                null,
                null);
    }
}
