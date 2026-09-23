package com.hvs.ws.back.app.output.bloco;

import com.hvs.ws.back.app.output.grade.ReadGradeOutput;
import com.hvs.ws.back.app.output.programa.ReadProgramaOutput;
import com.hvs.ws.back.domain.entity.bloco.Bloco;

public record ReadBlocoOutput(Long aId,
                              String aUuid,
                              String aStatusCode,
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
                aBloco.getStatus().getCode(),
                aBloco.getPrograma() != null ? ReadProgramaOutput.fromMinimal(aBloco.getPrograma()) : null,
                aBloco.getHorario(),
                aBloco.getGrade() != null ? ReadGradeOutput.fromMinimal(aBloco.getGrade()) : null,
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
