package com.hvs.webstore.back.domain.entity.television.bloco;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.television.grade.Grade;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Bloco extends Entity<BlocoId> {

    private final BlocoUuid uuid;
    private final BlocoStatus statusCode;
    private final Programa programa;
    private final String horario;
    private final Grade grade;

    private Bloco(final BlocoId id,
                 final BlocoUuid uuid,
                 final BlocoStatus statusCode,
                 final Programa programa,
                 final String horario,
                 final Grade grade) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.programa = programa;
        this.horario = horario;
        this.grade = grade;
    }

    public static Bloco create(final Long aProgramaId,
                               final String aHorario,
                               final Long aGradeId) {

        return new Bloco(
                BlocoId.from(-1L),
                BlocoUuid.unique(),
                BlocoStatus.ACTIVE,
                aProgramaId != null ? Programa.from(aProgramaId) : null,
                aHorario,
                aGradeId != null ? Grade.from(aGradeId) : null);
    }

    public static Bloco update(final Long aId,
                                final String aUuid,
                                final String aStatusCode,
                                final Long aProgramaId,
                                final String aHorario,
                                final Long aGradeId) {

        return new Bloco(
                aId != null ? BlocoId.from(aId) : null,
                aUuid != null ? BlocoUuid.from(aUuid) : null,
                aStatusCode != null ? BlocoStatus.findByCode(aStatusCode) : null,
                aProgramaId != null ? Programa.from(aProgramaId) : null,
                aHorario,
                aGradeId != null ? Grade.from(aGradeId) : null);
    }

    public static Bloco patch(final String aStatusCode,
                               final Long aProgramaId,
                               final String aHorario,
                               final Long aGradeId,
                               final Bloco aExisting) {

        return new Bloco(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? BlocoStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aProgramaId != null ? Programa.from(aProgramaId) : aExisting.getPrograma(),
                aHorario != null ? aHorario : aExisting.getHorario(),
                aGradeId != null ? Grade.from(aGradeId) : aExisting.getGrade());
    }

    public static Bloco from(final Long aId,
                              final String aUuid,
                              final String aStatusDesc,
                              final Programa aPrograma,
                              final String aHorario,
                              final Grade aGrade) {

        return new Bloco(
                aId != null ? BlocoId.from(aId) : null,
                aUuid != null ? BlocoUuid.from(aUuid) : null,
                aStatusDesc != null ? BlocoStatus.findByDesc(aStatusDesc) : null,
                aPrograma,
                aHorario,
                aGrade);
    }

    public static Bloco from(final Long aId) {

        return new Bloco(
                aId != null ? BlocoId.from(aId) : null,
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
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new BlocoValidator(aHandler, this).validate();
    }

    public BlocoUuid getUuid() {
        return uuid;
    }
    public BlocoStatus getStatusCode() {
        return statusCode;
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

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Bloco bloco = (Bloco) o;

        return Objects.equals(uuid, bloco.uuid) &&
                statusCode == bloco.statusCode &&
                Objects.equals(programa, bloco.programa) &&
                Objects.equals(horario, bloco.horario) &&
                Objects.equals(grade, bloco.grade);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                programa,
                horario,
                grade);
    }
}
