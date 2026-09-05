package com.hvs.webstore.back.app.output.television.programa;

import com.hvs.webstore.back.app.output.television.bloco.ReadBlocoOutput;
import com.hvs.webstore.back.app.output.television.episodio.ReadEpisodioOutput;
import com.hvs.webstore.back.app.output.television.genero.ReadGeneroOutput;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;

import java.time.format.DateTimeFormatter;
import java.util.List;

public record ReadProgramaOutput(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 String aNome,
                                 Boolean aEmProducao,
                                 String aTipoCode,
                                 Long aTemporadas,
                                 List<ReadEpisodioOutput> aEpisodios,
                                 String aLancamento,
                                 String aEncerramento,
                                 List<ReadBlocoOutput> aBlocos,
                                 String aSinopse,
                                 String aClassificacaoEtariaDesc,
                                 String aEstudio,
                                 String aDiretor,
                                 String aCapaUrl,
                                 String aTemporadaOriginal,
                                 String aRedeOriginal,
                                 String aTipoExibicaoDesc,
                                 String aTituloAlternativo,
                                 String aAudioIdiomas,
                                 String aLegendasDisponiveis,
                                 String aSiteOficial,
                                 List<ReadGeneroOutput> aGeneros){

    public static ReadProgramaOutput from(final Programa aPrograma) {

        return new ReadProgramaOutput(
                aPrograma.getId().getValue(),
                aPrograma.getUuid().getValue(),
                aPrograma.getStatus() != null ? aPrograma.getStatus().getCode() : null,
                aPrograma.getNome(),
                aPrograma.getEmProducao(),
                aPrograma.getTipo() != null ? aPrograma.getTipo().getDesc() : null,
                aPrograma.getTemporadas(),
                aPrograma.getEpisodios() != null ? aPrograma.getEpisodios().stream().map(ReadEpisodioOutput::fromSimple).toList() : null,
                aPrograma.getLancamento() != null ? aPrograma.getLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) : null,
                aPrograma.getEncerramento() != null ? aPrograma.getEncerramento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) : null,
                aPrograma.getBlocos() != null ? aPrograma.getBlocos().stream().map(ReadBlocoOutput::fromSimple).toList() : null,
                aPrograma.getSinopse(),
                aPrograma.getClassificacaoEtaria() != null ? aPrograma.getClassificacaoEtaria().getDesc() : null,
                aPrograma.getEstudio(),
                aPrograma.getDiretor(),
                aPrograma.getCapaUrl(),
                aPrograma.getTemporadaOriginal(),
                aPrograma.getRedeOriginal(),
                aPrograma.getTipoExibicao() != null ? aPrograma.getTipoExibicao().getDesc() : null,
                aPrograma.getTituloAlternativo(),
                aPrograma.getAudioIdiomas(),
                aPrograma.getLegendasDisponiveis(),
                aPrograma.getSiteOficial(),
                aPrograma.getGeneros() != null ? aPrograma.getGeneros().stream().map(ReadGeneroOutput::fromSimple).toList() : null);
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
                null,
                null,
                null,
                null,
                null,
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

    public static ReadProgramaOutput fromMinimal(final Programa aPrograma) {

        return new ReadProgramaOutput(
                aPrograma.getId().getValue(),
                aPrograma.getUuid().getValue(),
                null,
                aPrograma.getNome(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                aPrograma.getCapaUrl(),
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
