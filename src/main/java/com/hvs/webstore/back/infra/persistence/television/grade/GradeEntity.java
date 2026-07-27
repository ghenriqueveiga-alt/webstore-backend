package com.hvs.webstore.back.infra.persistence.television.grade;

import com.hvs.webstore.back.domain.entity.television.grade.Grade;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.television.bloco.BlocoEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "grade")
public class GradeEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "grade")
    private List<BlocoEntity> blocos;

    public GradeEntity() {

    }

    public GradeEntity(final Long id,
                       final String uuid,
                       final String statusDesc,
                       final String nome,
                       final String descricao,
                       final List<BlocoEntity> blocos) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.descricao = descricao;
        this.blocos = blocos;
    }

    public static GradeEntity from(final Grade aGrade) {

        return new GradeEntity(
                aGrade.getId().getValue() < 0 ? null : aGrade.getId().getValue(),
                aGrade.getUuid().getValue(),
                aGrade.getStatusCode().getDesc(),
                aGrade.getNome(),
                aGrade.getDescricao(),
                aGrade.getBlocos() != null && !aGrade.getBlocos().isEmpty() ?
                        aGrade.getBlocos().stream().map(bloco ->
                                BlocoEntity.from(bloco.getId().getValue())).toList() : null);

    }

    public static GradeEntity from(final Long aGradeId) {

        final var grade = new GradeEntity();
        grade.setId(aGradeId);

        return grade;
    }

    public Grade toDomain() {

        return Grade.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                blocos != null && !blocos.isEmpty() ?
                        blocos.stream().map(BlocoEntity::toDomainChildren).toList() : null);
    }

    public Grade toDomainChildren() {

        return Grade.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                blocos != null && !blocos.isEmpty() ?
                        blocos.stream().map(BlocoEntity::toDomainSimple).toList() : null);
    }

    public Grade toDomainSimple() {

        return Grade.from(
                getId(),
                uuid,
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
