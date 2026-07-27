package com.hvs.webstore.back.app.output.television.episodio;

import com.hvs.webstore.back.app.output.television.arquivo.ReadArquivoOutput;
import com.hvs.webstore.back.app.output.television.programa.ReadProgramaOutput;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;

public record ReadEpisodioOutput(Long aId,
                                 String aUuid,
                                 String aStatusDesc,
                                 ReadArquivoOutput aArquivo,
                                 String aTitulo,
                                 Long aNumero,
                                 Long aTemporada,
                                 ReadProgramaOutput aPrograma) {

    public static ReadEpisodioOutput from(final Episodio aEpisodio) {

        return new ReadEpisodioOutput(
                aEpisodio.getId().getValue(),
                aEpisodio.getUuid().getValue(),
                aEpisodio.getStatusCode().getDesc(),
                aEpisodio.getArquivo() != null ? ReadArquivoOutput.fromSimple(aEpisodio.getArquivo()) : null,
                aEpisodio.getTitulo(),
                aEpisodio.getNumero(),
                aEpisodio.getTemporada(),
                aEpisodio.getPrograma() != null ? ReadProgramaOutput.fromSimple(aEpisodio.getPrograma()) : null);
    }

    public static ReadEpisodioOutput fromSimple(final Episodio aEpisodio) {

        return new ReadEpisodioOutput(
                aEpisodio.getId().getValue(),
                aEpisodio.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
