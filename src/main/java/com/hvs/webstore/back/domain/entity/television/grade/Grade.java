package com.hvs.webstore.back.domain.entity.television.grade;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.television.bloco.Bloco;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Grade extends Entity<GradeId> {

    private final GradeUuid uuid;
    private final GradeStatus status;
    private final String nome;
    private final String descricao;
    private final List<Bloco> blocos;
    private final LocalDateTime periodoInicio;
    private final LocalDateTime periodoFim;
    private final Boolean gradeAtiva;

    private Grade(final GradeId id,
                 final GradeUuid uuid,
                 final GradeStatus status,
                 final String nome,
                 final String descricao,
                 final List<Bloco> blocos,
                 final LocalDateTime periodoInicio,
                 final LocalDateTime periodoFim,
                 final Boolean gradeAtiva) {

        super(id);
        this.uuid = uuid;
        this.status = status;
        this.nome = nome;
        this.descricao = descricao;
        this.blocos = blocos;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
        this.gradeAtiva = gradeAtiva;
    }

    public static Grade create(final String aNome,
                               final String aDescricao,
                               final List<Long> aBlocoIds,
                               final String aPeriodoInicio,
                               final String aPeriodoFim,
                               final Boolean aGradeAtiva) {

        return new Grade(
                GradeId.from(-1L),
                GradeUuid.unique(),
                GradeStatus.ACTIVE,
                aNome,
                aDescricao,
                aBlocoIds != null && !aBlocoIds.isEmpty() ?
                        aBlocoIds.stream().map(Bloco::from).toList() : null,
                aPeriodoInicio != null ? LocalDateTime.parse(aPeriodoInicio) : null,
                aPeriodoFim != null ? LocalDateTime.parse(aPeriodoFim) : null,
                aGradeAtiva);
    }

    public static Grade update(final Long aId,
                               final String aUuid,
                               final String aStatusCode,
                               final String aNome,
                               final String aDescricao,
                               final List<Long> aBlocoIds,
                               final String aPeriodoInicio,
                               final String aPeriodoFim,
                               final Boolean aGradeAtiva) {

        return new Grade(
                aId != null ? GradeId.from(aId) : null,
                aUuid != null ? GradeUuid.from(aUuid) : null,
                aStatusCode != null ? GradeStatus.findByCode(aStatusCode) : null,
                aNome,
                aDescricao,
                aBlocoIds != null && !aBlocoIds.isEmpty() ?
                        aBlocoIds.stream().map(Bloco::from).toList() : null,
                aPeriodoInicio != null ? LocalDateTime.parse(aPeriodoInicio) : null,
                aPeriodoFim != null ? LocalDateTime.parse(aPeriodoFim) : null,
                aGradeAtiva);
    }

    public static Grade patch(final String aStatusCode,
                              final String aNome,
                              final String aDescricao,
                              final List<Long> aBlocoIds,
                              final String aPeriodoInicio,
                              final String aPeriodoFim,
                              final Boolean aGradeAtiva,
                              final Grade aExisting) {

        return new Grade(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? GradeStatus.findByCode(aStatusCode) : aExisting.getStatus(),
                aNome != null ? aNome : aExisting.getNome(),
                aDescricao != null ? aDescricao : aExisting.getDescricao(),
                aBlocoIds != null && !aBlocoIds.isEmpty() ?
                        aBlocoIds.stream().map(Bloco::from).toList() : aExisting.getBlocos(),
                aPeriodoInicio != null ? LocalDateTime.parse(aPeriodoInicio) : aExisting.getPeriodoInicio(),
                aPeriodoFim != null ? LocalDateTime.parse(aPeriodoFim) : aExisting.getPeriodoFim(),
                aGradeAtiva != null ? aGradeAtiva : aExisting.getGradeAtiva());
    }

    public static Grade from(final Long aId,
                             final String aUuid,
                             final String aStatusDesc,
                             final String aNome,
                             final String aDescricao,
                             final List<Bloco> aBlocos,
                             final LocalDateTime aPeriodoInicio,
                             final LocalDateTime aPeriodoFim,
                             final Boolean aGradeAtiva) {

        return new Grade(
                aId != null ? GradeId.from(aId) : null,
                aUuid != null ? GradeUuid.from(aUuid) : null,
                aStatusDesc != null ? GradeStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aDescricao,
                aBlocos,
                aPeriodoInicio,
                aPeriodoFim,
                aGradeAtiva);
    }

    public static Grade from(final Long aId) {

        return new Grade(
                aId != null ? GradeId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Grade from(final String aUuid) {

        return new Grade(
                null,
                aUuid != null ? GradeUuid.from(aUuid) : null,
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

        new GradeValidator(aHandler, this).validate();
    }

    public GradeUuid getUuid() {
        return uuid;
    }
    public GradeStatus getStatus() {
        return status;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public List<Bloco> getBlocos() {
        return blocos;
    }
    public LocalDateTime getPeriodoInicio() {
        return periodoInicio;
    }
    public LocalDateTime getPeriodoFim() {
        return periodoFim;
    }
    public Boolean getGradeAtiva() {
        return gradeAtiva;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Grade grade = (Grade) o;

        return Objects.equals(uuid, grade.uuid) &&
                status == grade.status &&
                Objects.equals(nome, grade.nome) &&
                Objects.equals(descricao, grade.descricao) &&
                Objects.equals(blocos, grade.blocos) &&
                Objects.equals(periodoInicio, grade.periodoInicio) &&
                Objects.equals(periodoFim, grade.periodoFim) &&
                Objects.equals(gradeAtiva, grade.gradeAtiva);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                status,
                nome,
                descricao,
                blocos,
                periodoInicio,
                periodoFim,
                gradeAtiva);
    }
}
