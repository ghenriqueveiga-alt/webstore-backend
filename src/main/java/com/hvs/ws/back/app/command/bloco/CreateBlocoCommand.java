package com.hvs.ws.back.app.command.bloco;

public record CreateBlocoCommand(Long aProgramaId,
                                 String aHorario,
                                 Long aGradeId,
                                 String aDiaSemanaCode,
                                 String aFaixaHorarioCode,
                                 String aTipoBlocoCode) {

    public static CreateBlocoCommand from(final Long aProgramaId,
                                          final String aHorario,
                                          final Long aGradeId,
                                          final String aDiaSemanaCode,
                                          final String aFaixaHorarioCode,
                                          final String aTipoBlocoCode) {

        return new CreateBlocoCommand(
                aProgramaId,
                aHorario,
                aGradeId,
                aDiaSemanaCode,
                aFaixaHorarioCode,
                aTipoBlocoCode);
    }
}