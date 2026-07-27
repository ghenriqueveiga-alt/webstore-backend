package com.hvs.webstore.back.app.command.television.bloco;

public record CreateBlocoCommand(Long aProgramaId,
                                 String aHorario,
                                 Long aGradeId) {

    public static CreateBlocoCommand from(final Long aProgramaId,
                                          final String aHorario,
                                          final Long aGradeId) {

        return new CreateBlocoCommand(
                aProgramaId,
                aHorario,
                aGradeId);
    }
}