package com.hvs.webstore.back.app.command.television.bloco;

public record UpdateBlocoCommand(Long aId,
                                 String aUuid,
                                 String aStatusDesc,
                                 Long aProgramaId,
                                 String aHorario,
                                 Long aGradeId,
                                 String aDiaSemanaCode,
                                 String aFaixaHorarioCode,
                                 String aTipoBlocoCode) {

    public static UpdateBlocoCommand from(final Long aId,
                                          final UpdateBlocoCommand aInput) {

        return new UpdateBlocoCommand(
                aId,
                null,
                aInput.aStatusDesc,
                aInput.aProgramaId,
                aInput.aHorario,
                aInput.aGradeId,
                aInput.aDiaSemanaCode,
                aInput.aFaixaHorarioCode,
                aInput.aTipoBlocoCode);
    }

    public static UpdateBlocoCommand from(final String aUuid,
                                          final UpdateBlocoCommand aInput) {

        return new UpdateBlocoCommand(
                null,
                aUuid,
                aInput.aStatusDesc,
                aInput.aProgramaId,
                aInput.aHorario,
                aInput.aGradeId,
                aInput.aDiaSemanaCode,
                aInput.aFaixaHorarioCode,
                aInput.aTipoBlocoCode);
    }
}