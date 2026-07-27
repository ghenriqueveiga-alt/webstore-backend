package com.hvs.webstore.back.app.output.television.grade;

import com.hvs.webstore.back.app.output.television.bloco.ReadBlocoOutput;
import com.hvs.webstore.back.domain.entity.television.grade.Grade;

import java.util.List;

public record ReadGradeOutput(Long aId,
                              String aUuid,
                              String aStatusDesc,
                              String aNome,
                              String aDescricao,
                              List<ReadBlocoOutput> aBlocos) {

    public static ReadGradeOutput from(final Grade aGrade) {

        return new ReadGradeOutput(
                aGrade.getId().getValue(),
                aGrade.getUuid().getValue(),
                aGrade.getStatusCode().getDesc(),
                aGrade.getNome(),
                aGrade.getDescricao(),
                aGrade.getBlocos().stream().map(ReadBlocoOutput::fromSimple).toList());
    }

    public static ReadGradeOutput fromSimple(final Grade aGrade) {

        return new ReadGradeOutput(
                aGrade.getId().getValue(),
                aGrade.getUuid().getValue(),
                null,
                null,
                null,
                null);
    }
}
