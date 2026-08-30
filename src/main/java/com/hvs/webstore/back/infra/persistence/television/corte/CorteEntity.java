package com.hvs.webstore.back.infra.persistence.television.corte;

import com.hvs.webstore.back.domain.entity.television.corte.Corte;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.television.arquivo.ArquivoEntity;
import com.hvs.webstore.back.infra.persistence.television.episodio.EpisodioEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "corte")
public class CorteEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "arquivo_id", referencedColumnName = "id")
    private ArquivoEntity arquivo;
    private String tipoDesc;
    private String duracao;

    @ManyToOne
    @JoinColumn(name = "episodio_id")
    @JsonIgnore
    private EpisodioEntity episodio;

    @Column(name = "inicio")
    private LocalTime inicio;

    @Column(name = "fim")
    private LocalTime fim;

    public CorteEntity() {

    }

    public CorteEntity(final Long id,
                       final String uuid,
                       final String statusDesc,
                       final ArquivoEntity arquivo,
                       final String tipoDesc,
                       final String duracao,
                       final EpisodioEntity episodio,
                       final LocalTime inicio,
                       final LocalTime fim) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.arquivo = arquivo;
        this.tipoDesc = tipoDesc;
        this.duracao = duracao;
        this.episodio = episodio;
        this.inicio = inicio;
        this.fim = fim;
    }

    public static CorteEntity from(final Corte aCorte) {

        return new CorteEntity(
                aCorte.getId().getValue() < 0 ? null : aCorte.getId().getValue(),
                aCorte.getUuid().getValue(),
                aCorte.getStatus().getDesc(),
                aCorte.getArquivo() != null ? ArquivoEntity.from(aCorte.getArquivo().getId().getValue()) : null,
                aCorte.getTipo().getDesc(),
                aCorte.getDuracao(),
                aCorte.getEpisodio() != null ? EpisodioEntity.from(aCorte.getEpisodio().getId().getValue()) : null,
                aCorte.getInicio(),
                aCorte.getFim());
    }

    public static CorteEntity from(final Long aCorteId) {

        final var corte = new CorteEntity();
        corte.setId(aCorteId);

        return corte;
    }

    public Corte toDomain() {

        return Corte.from(
                getId(),
                uuid,
                statusDesc,
                arquivo != null ? arquivo.toDomainChildren(): null,
                tipoDesc,
                duracao,
                episodio != null ? episodio.toDomainChildren(): null,
                inicio,
                fim);
    }

    public Corte toDomainChildren() {

        return Corte.from(
                getId(),
                uuid,
                statusDesc,
                arquivo != null ? arquivo.toDomainSimple(): null,
                tipoDesc,
                duracao,
                episodio != null ? episodio.toDomainSimple(): null,
                inicio,
                fim);
    }

    public Corte toDomainSimple() {

        return Corte.from(
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

    public LocalTime getInicio() {
        return inicio;
    }
    public void setInicio(final LocalTime inicio) {
        this.inicio = inicio;
    }
    public LocalTime getFim() {
        return fim;
    }
    public void setFim(final LocalTime fim) {
        this.fim = fim;
    }
}
