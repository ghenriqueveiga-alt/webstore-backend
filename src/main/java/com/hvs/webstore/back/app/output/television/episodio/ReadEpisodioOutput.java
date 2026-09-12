package com.hvs.webstore.back.app.output.television.episodio;

import com.hvs.webstore.back.app.output.television.arquivo.ReadArquivoOutput;
import com.hvs.webstore.back.app.output.television.programa.ReadProgramaOutput;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detalhes de um episódio")
public record ReadEpisodioOutput(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 ReadArquivoOutput aArquivo,
                                 String aTitulo,
                                 Long aNumero,
                                 Long aTemporada,
                                 String aCapaUrl,
                                 ReadProgramaOutput aPrograma,
                                 String aDuracao) {

    public static ReadEpisodioOutput from(final Episodio aEpisodio) {

        return new ReadEpisodioOutput(
                aEpisodio.getId().getValue(),
                aEpisodio.getUuid().getValue(),
                aEpisodio.getStatus().getDesc(),
                aEpisodio.getArquivo() != null ? ReadArquivoOutput.fromSimple(aEpisodio.getArquivo()) : null,
                aEpisodio.getTitulo(),
                aEpisodio.getNumero(),
                aEpisodio.getTemporada(),
                aEpisodio.getCapaUrl(),
                aEpisodio.getPrograma() != null ? ReadProgramaOutput.fromMinimal(aEpisodio.getPrograma()) : null,
                aEpisodio.getDuracao());
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
                null,
                null,
                null);
    }
}
