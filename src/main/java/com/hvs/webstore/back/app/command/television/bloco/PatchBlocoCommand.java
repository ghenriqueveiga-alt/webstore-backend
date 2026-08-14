package com.hvs.webstore.back.app.command.television.bloco;

public record PatchBlocoCommand(Long aId,
                                String aUuid,
                                String aStatusDesc,
                                Long aProgramaId,
                                String aHorario,
                                Long aGradeId,
                                String aDiaSemanaCode,
                                String aFaixaHorarioCode,
                                String aTipoBlocoCode) {

    public static PatchBlocoCommand from(final Long aId,
                                         final PatchBlocoCommand aInput) {

        return new PatchBlocoCommand(
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

    public static PatchBlocoCommand from(final String aUuid,
                                         final PatchBlocoCommand aInput) {

        return new PatchBlocoCommand(
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