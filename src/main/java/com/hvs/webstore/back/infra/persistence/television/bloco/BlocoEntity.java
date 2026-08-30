package com.hvs.webstore.back.infra.persistence.television.bloco;

import com.hvs.webstore.back.domain.entity.television.bloco.Bloco;
import com.hvs.webstore.back.domain.entity.television.bloco.BlocoTipo;
import com.hvs.webstore.back.domain.entity.television.bloco.DiaSemana;
import com.hvs.webstore.back.domain.entity.television.bloco.FaixaHorario;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.television.grade.GradeEntity;
import com.hvs.webstore.back.infra.persistence.television.programa.ProgramaEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "bloco")
public class BlocoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "programa_id", referencedColumnName = "id")
    private ProgramaEntity programa;
    private String horario;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private GradeEntity grade;
    private String diaSemanaDesc;
    private String faixaHorarioDesc;
    private String tipoBlocoDesc;

    public BlocoEntity() {

    }

    public BlocoEntity(final Long id,
                       final String uuid,
                       final String statusDesc,
                       final ProgramaEntity programa,
                       final String horario,
                       final GradeEntity grade,
                       final String diaSemanaDesc,
                       final String faixaHorarioDesc,
                       final String tipoBlocoDesc) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.programa = programa;
        this.horario = horario;
        this.grade = grade;
        this.diaSemanaDesc = diaSemanaDesc;
        this.faixaHorarioDesc = faixaHorarioDesc;
        this.tipoBlocoDesc = tipoBlocoDesc;
    }

    public static BlocoEntity from(final Bloco aBloco) {

        return new BlocoEntity(
                aBloco.getId().getValue() < 0 ? null : aBloco.getId().getValue(),
                aBloco.getUuid().getValue(),
                aBloco.getStatus().getDesc(),
                aBloco.getPrograma() != null ? ProgramaEntity.from(aBloco.getPrograma().getId().getValue()) : null,
                aBloco.getHorario(),
                aBloco.getGrade() != null ? GradeEntity.from(aBloco.getGrade().getId().getValue()) : null,
                aBloco.getDiaSemana() != null ? aBloco.getDiaSemana().getDesc() : null,
                aBloco.getFaixaHorario() != null ? aBloco.getFaixaHorario().getDesc() : null,
                aBloco.getTipoBloco() != null ? aBloco.getTipoBloco().getDesc() : null);
    }

    public static BlocoEntity from(final Long aBlocoId) {

        final var bloco = new BlocoEntity();
        bloco.setId(aBlocoId);

        return bloco;
    }

    public Bloco toDomain() {

        return Bloco.from(
                getId(),
                uuid,
                statusDesc,
                programa != null ? programa.toDomainChildren() : null,
                horario,
                grade != null ? grade.toDomainChildren() : null,
                diaSemanaDesc,
                faixaHorarioDesc,
                tipoBlocoDesc);
    }

    public Bloco toDomainChildren() {

        return Bloco.from(
                getId(),
                uuid,
                statusDesc,
                programa != null ? programa.toDomainSimple() : null,
                horario,
                grade != null ? grade.toDomainSimple() : null,
                diaSemanaDesc,
                faixaHorarioDesc,
                tipoBlocoDesc);
    }

    public Bloco toDomainSimple() {

        return Bloco.from(
                getId(),
                uuid,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public Long getId() {
        return this.id;
    }
    public void setId(final Long aId) {
        this.id = aId;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
