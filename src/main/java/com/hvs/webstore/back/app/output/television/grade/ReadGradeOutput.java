package com.hvs.webstore.back.app.output.television.grade;

import com.hvs.webstore.back.app.output.television.bloco.ReadBlocoOutput;
import com.hvs.webstore.back.domain.entity.television.grade.Grade;

import java.time.format.DateTimeFormatter;
import java.util.List;

public record ReadGradeOutput(Long aId,
                              String aUuid,
                              String aStatusDesc,
                              String aNome,
                              String aDescricao,
                              List<ReadBlocoOutput> aBlocos,
                              String aPeriodoInicio,
                              String aPeriodoFim,
                              Boolean aGradeAtiva) {

    public static ReadGradeOutput from(final Grade aGrade) {

        return new ReadGradeOutput(
                aGrade.getId().getValue(),
                aGrade.getUuid().getValue(),
                aGrade.getStatusCode().getDesc(),
                aGrade.getNome(),
                aGrade.getDescricao(),
                aGrade.getBlocos() != null ? aGrade.getBlocos().stream().map(ReadBlocoOutput::fromSimple).toList() : null,
                aGrade.getPeriodoInicio() != null ? aGrade.getPeriodoInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) : null,
                aGrade.getPeriodoFim() != null ? aGrade.getPeriodoFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) : null,
                aGrade.getGradeAtiva());
    }

    public static ReadGradeOutput fromSimple(final Grade aGrade) {

        return new ReadGradeOutput(
                aGrade.getId().getValue(),
                aGrade.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
