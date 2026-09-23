package com.hvs.ws.back.app.output.arquivo;

import com.hvs.ws.back.domain.entity.arquivo.Arquivo;

public record ReadArquivoOutput(Long aId,
                                String aUuid,
                                String aStatusCode,
                                String aNome,
                                String aTipo,
                                Long aTamanho,
                                String aCaminho) {

    public static ReadArquivoOutput from(final Arquivo aArquivo) {

        return new ReadArquivoOutput(
                aArquivo.getId().getValue(),
                aArquivo.getUuid().getValue(),
                aArquivo.getStatus().getCode(),
                aArquivo.getNome(),
                aArquivo.getTipo(),
                aArquivo.getTamanho(),
                aArquivo.getCaminho());
    }

    public static ReadArquivoOutput fromSimple(final Arquivo aArquivo) {

        return new ReadArquivoOutput(
                aArquivo.getId().getValue(),
                aArquivo.getUuid().getValue(),
                aArquivo.getStatus().getCode(),
                null,
                null,
                null,
                null);
    }
}
