package com.hvs.webstore.back.infra.persistence.television.programa;

import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.television.bloco.BlocoEntity;
import com.hvs.webstore.back.infra.persistence.television.episodio.EpisodioEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "programa")
public class ProgramaEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private Boolean emProducao;
    private String tipoCode;
    private Long temporadas;

    @OneToMany(mappedBy = "programa", cascade = CascadeType.ALL)
    private List<EpisodioEntity> episodios;
    private String lancamento;
    private String encerramento;

    @OneToMany(mappedBy = "programa", cascade = CascadeType.ALL)
    private List<BlocoEntity> blocos;

    public ProgramaEntity() {

    }

    public ProgramaEntity(final Long id,
                          final String uuid,
                          final String statusDesc,
                          final String nome,
                          final Boolean emProducao,
                          final String tipoCode,
                          final Long temporadas,
                          final List<EpisodioEntity> episodios,
                          final String lancamento,
                          final String encerramento,
                          final List<BlocoEntity> blocos) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.emProducao = emProducao;
        this.tipoCode = tipoCode;
        this.temporadas = temporadas;
        this.episodios = episodios;
        this.lancamento = lancamento;
        this.encerramento = encerramento;
        this.blocos = blocos;
    }

    public static ProgramaEntity from(final Programa aPrograma) {

        return new ProgramaEntity(
                aPrograma.getId().getValue() < 0 ? null : aPrograma.getId().getValue(),
                aPrograma.getUuid().getValue(),
                aPrograma.getStatusCode().getDesc(),
                aPrograma.getNome(),
                aPrograma.getEmProducao(),
                aPrograma.getTipo().getCode(),
                aPrograma.getTemporadas(),
                aPrograma.getEpisodios() != null && !aPrograma.getEpisodios().isEmpty() ?
                        aPrograma.getEpisodios().stream().map(episodio ->
                                EpisodioEntity.from(episodio.getId().getValue())).toList() : null,
                aPrograma.getLancamento().toString(),
                aPrograma.getEncerramento().toString(),
                aPrograma.getBlocos() != null && !aPrograma.getBlocos().isEmpty() ?
                        aPrograma.getBlocos().stream().map(bloco ->
                                BlocoEntity.from(bloco.getId().getValue())).toList() : null);

    }

    public static ProgramaEntity from(final Long aProgramaId) {

        final var programa = new ProgramaEntity();
        programa.setId(aProgramaId);

        return programa;
    }

    public Programa toDomain() {

        return Programa.from(getId(),
                             uuid,
                             statusDesc,
                             nome,
                             emProducao,
                             tipoCode,
                             temporadas,
                             episodios != null && !episodios.isEmpty() ?
                                     episodios.stream().map(EpisodioEntity::toDomainChildren).toList() : null,
                             lancamento,
                             encerramento,
                             blocos != null && !blocos.isEmpty() ?
                                     blocos.stream().map(BlocoEntity::toDomainChildren).toList() : null);
    }

    public Programa toDomainChildren() {

        return Programa.from(getId(),
                             uuid,
                             statusDesc,
                             nome,
                             emProducao,
                             tipoCode,
                             temporadas,
                             episodios != null && !episodios.isEmpty() ?
                                     episodios.stream().map(EpisodioEntity::toDomainSimple).toList() : null,
                             lancamento,
                             encerramento,
                             blocos != null && !blocos.isEmpty() ?
                                     blocos.stream().map(BlocoEntity::toDomainSimple).toList() : null);
    }

    public Programa toDomainSimple() {

        return Programa.from(getId(),
                             uuid,
                             null,
                             null,
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
