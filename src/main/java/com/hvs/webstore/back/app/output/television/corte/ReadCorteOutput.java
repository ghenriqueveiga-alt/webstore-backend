package com.hvs.webstore.back.app.output.television.corte;

import com.hvs.webstore.back.app.output.television.arquivo.ReadArquivoOutput;
import com.hvs.webstore.back.app.output.television.episodio.ReadEpisodioOutput;
import com.hvs.webstore.back.domain.entity.television.corte.Corte;

public record ReadCorteOutput(Long aId,
                              String aUuid,
                              String aStatusDesc,
                              ReadArquivoOutput aArquivo,
                              String aTipoDesc,
                              String aDuracao,
                              ReadEpisodioOutput episodio) {

    public static ReadCorteOutput from(final Corte aCorte) {

        return new ReadCorteOutput(
                aCorte.getId().getValue(),
                aCorte.getUuid().getValue(),
                aCorte.getStatusCode().getDesc(),
                aCorte.getEpisodio() != null ? ReadArquivoOutput.fromSimple(aCorte.getArquivo()) : null,
                aCorte.getTipo().getDesc(),
                aCorte.getDuracao(),
                aCorte.getEpisodio() != null ? ReadEpisodioOutput.fromSimple(aCorte.getEpisodio()) : null);
    }

    public static ReadCorteOutput fromSimple(final Corte aCorte) {

        return new ReadCorteOutput(
                aCorte.getId().getValue(),
                aCorte.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null);
    }
}
