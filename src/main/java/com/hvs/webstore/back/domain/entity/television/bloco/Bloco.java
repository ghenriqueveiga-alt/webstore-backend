package com.hvs.webstore.back.domain.entity.television.bloco;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.television.grade.Grade;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Bloco extends Entity<BlocoId> {

    private final BlocoUuid uuid;
    private final BlocoStatus status;
    private final Programa programa;
    private final String horario;
    private final Grade grade;
    private final DiaSemana diaSemana;
    private final FaixaHorario faixaHorario;
    private final BlocoTipo tipoBloco;

    private Bloco(final BlocoId id,
                  final BlocoUuid uuid,
                  final BlocoStatus status,
                  final Programa programa,
                  final String horario,
                  final Grade grade,
                  final DiaSemana diaSemana,
                  final FaixaHorario faixaHorario,
                  final BlocoTipo tipoBloco) {

        super(id);
        this.uuid = uuid;
        this.status = status;
        this.programa = programa;
        this.horario = horario;
        this.grade = grade;
        this.diaSemana = diaSemana;
        this.faixaHorario = faixaHorario;
        this.tipoBloco = tipoBloco;
    }

    public static Bloco create(final Long aProgramaId,
                               final String aHorario,
                               final Long aGradeId,
                               final String aDiaSemanaCode,
                               final String aFaixaHorarioCode,
                               final String aTipoBlocoCode) {

        return new Bloco(
                BlocoId.from(-1L),
                BlocoUuid.unique(),
                BlocoStatus.ACTIVE,
                aProgramaId != null ? Programa.from(aProgramaId) : null,
                aHorario,
                aGradeId != null ? Grade.from(aGradeId) : null,
                aDiaSemanaCode != null ? DiaSemana.findByCode(aDiaSemanaCode) : null,
                aFaixaHorarioCode != null ? FaixaHorario.findByCode(aFaixaHorarioCode) : null,
                aTipoBlocoCode != null ? BlocoTipo.findByCode(aTipoBlocoCode) : null);
    }

    public static Bloco update(final Long aId,
                               final String aUuid,
                               final String aStatusCode,
                               final Long aProgramaId,
                               final String aHorario,
                               final Long aGradeId,
                               final String aDiaSemanaCode,
                               final String aFaixaHorarioCode,
                               final String aTipoBlocoCode) {

        return new Bloco(
                aId != null ? BlocoId.from(aId) : null,
                aUuid != null ? BlocoUuid.from(aUuid) : null,
                aStatusCode != null ? BlocoStatus.findByCode(aStatusCode) : null,
                aProgramaId != null ? Programa.from(aProgramaId) : null,
                aHorario,
                aGradeId != null ? Grade.from(aGradeId) : null,
                aDiaSemanaCode != null ? DiaSemana.findByCode(aDiaSemanaCode) : null,
                aFaixaHorarioCode != null ? FaixaHorario.findByCode(aFaixaHorarioCode) : null,
                aTipoBlocoCode != null ? BlocoTipo.findByCode(aTipoBlocoCode) : null);
    }

    public static Bloco patch(final String aStatusCode,
                              final Long aProgramaId,
                              final String aHorario,
                              final Long aGradeId,
                              final String aDiaSemanaCode,
                              final String aFaixaHorarioCode,
                              final String aTipoBlocoCode,
                              final Bloco aBlocoDB) {

        return new Bloco(
                aBlocoDB.getId(),
                aBlocoDB.getUuid(),
                aStatusCode != null ? BlocoStatus.findByCode(aStatusCode) : aBlocoDB.getStatus(),
                aProgramaId != null ? Programa.from(aProgramaId) : aBlocoDB.getPrograma(),
                aHorario != null ? aHorario : aBlocoDB.getHorario(),
                aGradeId != null ? Grade.from(aGradeId) : aBlocoDB.getGrade(),
                aDiaSemanaCode != null ? DiaSemana.findByCode(aDiaSemanaCode) : aBlocoDB.getDiaSemana(),
                aFaixaHorarioCode != null ? FaixaHorario.findByCode(aFaixaHorarioCode) : aBlocoDB.getFaixaHorario(),
                aTipoBlocoCode != null ? BlocoTipo.findByCode(aTipoBlocoCode) : aBlocoDB.getTipoBloco());
    }

    public static Bloco from(final Long aId,
                             final String aUuid,
                             final String aStatusDesc,
                             final Programa aPrograma,
                             final String aHorario,
                             final Grade aGrade,
                             final String aDiaSemanaDesc,
                             final String aFaixaHorarioDesc,
                             final String aTipoBlocoDesc) {

        return new Bloco(
                aId != null ? BlocoId.from(aId) : null,
                aUuid != null ? BlocoUuid.from(aUuid) : null,
                aStatusDesc != null ? BlocoStatus.findByDesc(aStatusDesc) : null,
                aPrograma,
                aHorario,
                aGrade,
                aDiaSemanaDesc != null ? DiaSemana.findByDesc(aDiaSemanaDesc) : null,
                aFaixaHorarioDesc != null ? FaixaHorario.findByDesc(aFaixaHorarioDesc) : null,
                aTipoBlocoDesc != null ? BlocoTipo.findByDesc(aTipoBlocoDesc) : null);
    }

    public static Bloco from(final Long aId) {

        return new Bloco(
                aId != null ? BlocoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Bloco from(final String aUuid) {

        return new Bloco(
                null,
                aUuid != null ? BlocoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new BlocoValidator(aHandler, this).validate();
    }

    public BlocoUuid getUuid() {
        return uuid;
    }
    public BlocoStatus getStatus() {
        return status;
    }
    public Programa getPrograma() {
        return programa;
    }
    public String getHorario() {
        return horario;
    }
    public Grade getGrade() {
        return grade;
    }
    public DiaSemana getDiaSemana() {
        return diaSemana;
    }
    public FaixaHorario getFaixaHorario() {
        return faixaHorario;
    }
    public BlocoTipo getTipoBloco() {
        return tipoBloco;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Bloco bloco = (Bloco) o;

        return Objects.equals(uuid, bloco.uuid) &&
                status == bloco.status &&
                Objects.equals(programa, bloco.programa) &&
                Objects.equals(horario, bloco.horario) &&
                Objects.equals(grade, bloco.grade) &&
                diaSemana == bloco.diaSemana &&
                faixaHorario == bloco.faixaHorario &&
                tipoBloco == bloco.tipoBloco;
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                status,
                programa,
                horario,
                grade,
                diaSemana,
                faixaHorario,
                tipoBloco);
    }
}
