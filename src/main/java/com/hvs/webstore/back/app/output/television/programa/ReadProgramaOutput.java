package com.hvs.webstore.back.app.output.television.programa;

import com.hvs.webstore.back.app.output.television.bloco.ReadBlocoOutput;
import com.hvs.webstore.back.app.output.television.episodio.ReadEpisodioOutput;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;

import java.time.format.DateTimeFormatter;
import java.util.List;

public record ReadProgramaOutput(Long aId,
                                 String aUuid,
                                 String aStatusDesc,
                                 String aNome,
                                 Boolean aEmProducao,
                                 String aTipoDesc,
                                 Long aTemporadas,
                                 List<ReadEpisodioOutput> aEpisodios,
                                 String aLancamento,
                                 String aEncerramento,
                                 List<ReadBlocoOutput> aBlocos){

    public static ReadProgramaOutput from(final Programa aPrograma) {

        return new ReadProgramaOutput(
                aPrograma.getId().getValue(),
                aPrograma.getUuid().getValue(),
                aPrograma.getStatusCode().getDesc(),
                aPrograma.getNome(),
                aPrograma.getEmProducao(),
                aPrograma.getTipo().getDesc(),
                aPrograma.getTemporadas(),
                aPrograma.getEpisodios().stream().map(ReadEpisodioOutput::fromSimple).toList(),
                aPrograma.getLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                aPrograma.getEncerramento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                aPrograma.getBlocos().stream().map(ReadBlocoOutput::fromSimple).toList());
    }

    public static ReadProgramaOutput fromSimple(final Programa aPrograma) {

        return new ReadProgramaOutput(
                aPrograma.getId().getValue(),
                aPrograma.getUuid().getValue(),
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
