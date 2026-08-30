package com.hvs.webstore.back.app.output.television.corte;

import com.hvs.webstore.back.app.output.television.arquivo.ReadArquivoOutput;
import com.hvs.webstore.back.app.output.television.episodio.ReadEpisodioOutput;
import com.hvs.webstore.back.domain.entity.television.corte.Corte;

import java.time.LocalTime;

public record ReadCorteOutput(Long aId,
                              String aUuid,
                              String aStatusCode,
                              ReadArquivoOutput aArquivo,
                              String aTipoCode,
                              String aDuracao,
                              ReadEpisodioOutput episodio,
                              LocalTime aInicio,
                              LocalTime aFim) {

    public static ReadCorteOutput from(final Corte aCorte) {

        return new ReadCorteOutput(
                aCorte.getId().getValue(),
                aCorte.getUuid().getValue(),
                aCorte.getStatus().getCode(),
                aCorte.getArquivo() != null ? ReadArquivoOutput.fromSimple(aCorte.getArquivo()) : null,
                aCorte.getTipo().getCode(),
                aCorte.getDuracao(),
                aCorte.getEpisodio() != null ? ReadEpisodioOutput.fromSimple(aCorte.getEpisodio()) : null,
                aCorte.getInicio(),
                aCorte.getFim());
    }

    public static ReadCorteOutput fromSimple(final Corte aCorte) {

        return new ReadCorteOutput(
                aCorte.getId().getValue(),
                aCorte.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
