package com.hvs.webstore.back.app.output.television.bloco;

import com.hvs.webstore.back.app.output.television.grade.ReadGradeOutput;
import com.hvs.webstore.back.app.output.television.programa.ReadProgramaOutput;
import com.hvs.webstore.back.domain.entity.television.bloco.Bloco;

public record ReadBlocoOutput(Long aId,
                              String aUuid,
                              String aStatusDesc,
                              ReadProgramaOutput aPrograma,
                              String aHorario,
                              ReadGradeOutput aGrade,
                              String aDiaSemanaDesc,
                              String aFaixaHorarioDesc,
                              String aTipoBlocoDesc) {

    public static ReadBlocoOutput from(final Bloco aBloco) {

        return new ReadBlocoOutput(
                aBloco.getId().getValue(),
                aBloco.getUuid().getValue(),
                aBloco.getStatusCode().getDesc(),
                aBloco.getPrograma() != null ? ReadProgramaOutput.fromSimple(aBloco.getPrograma()) : null,
                aBloco.getHorario(),
                aBloco.getGrade() != null ? ReadGradeOutput.fromSimple(aBloco.getGrade()) : null,
                aBloco.getDiaSemana() != null ? aBloco.getDiaSemana().getDesc() : null,
                aBloco.getFaixaHorario() != null ? aBloco.getFaixaHorario().getDesc() : null,
                aBloco.getTipoBloco() != null ? aBloco.getTipoBloco().getDesc() : null);
    }

    public static ReadBlocoOutput fromSimple(final Bloco aBloco) {

        return new ReadBlocoOutput(
                aBloco.getId().getValue(),
                aBloco.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
