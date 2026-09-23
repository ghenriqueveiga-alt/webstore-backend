package com.hvs.ws.back.infra.persistence.episodio;

import com.hvs.ws.back.domain.entity.episodio.Episodio;
import com.hvs.ws.back.infra.persistence.BasicEntity;
import com.hvs.ws.back.infra.persistence.arquivo.ArquivoEntity;
import com.hvs.ws.back.infra.persistence.programa.ProgramaEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "episodio")
public class EpisodioEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arquivo_id", referencedColumnName = "id")
    private ArquivoEntity arquivo;
    private String titulo;
    private Long numero;
    private Long temporada;
    private String capaUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "programa_id")
    private ProgramaEntity programa;
    private Long parte;
    private String duracao;

    public EpisodioEntity() {

    }

    public EpisodioEntity(final Long id,
                          final String uuid,
                          final String statusDesc,
                          final ArquivoEntity arquivo,
                          final String titulo,
                          final Long numero,
                          final Long temporada,
                          final String capaUrl,
                          final ProgramaEntity programa,
                          final Long parte,
                          final String duracao) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.arquivo = arquivo;
        this.titulo = titulo;
        this.numero = numero;
        this.temporada = temporada;
        this.capaUrl = capaUrl;
        this.programa = programa;
        this.parte = parte;
        this.duracao = duracao;
    }

    public static EpisodioEntity from(final Episodio aEpisodio) {

        return new EpisodioEntity(
                aEpisodio.getId().getValue() < 0 ? null : aEpisodio.getId().getValue(),
                aEpisodio.getUuid().getValue(),
                aEpisodio.getStatus().getDesc(),
                aEpisodio.getArquivo() != null ? ArquivoEntity.from(aEpisodio.getArquivo()) : null,
                aEpisodio.getTitulo(),
                aEpisodio.getNumero(),
                aEpisodio.getTemporada(),
                aEpisodio.getCapaUrl(),
                aEpisodio.getPrograma() != null ? ProgramaEntity.from(aEpisodio.getPrograma()) : null,
                aEpisodio.getParte(),
                aEpisodio.getDuracao());
    }

    public static EpisodioEntity from(final Long aEpisodioId) {

        final var episodio = new EpisodioEntity();
        episodio.setId(aEpisodioId);

        return episodio;
    }

    public Episodio toDomain() {

        return Episodio.from(
                getId(),
                uuid,
                statusDesc,
                arquivo != null ? arquivo.toDomainChildren() : null,
                titulo,
                numero,
                temporada,
                capaUrl,
                programa != null ? programa.toDomainChildren() : null,
                parte,
                duracao);
    }

    public Episodio toDomainChildren() {

        return Episodio.from(
                getId(),
                uuid,
                statusDesc,
                arquivo != null ? arquivo.toDomainChildren() : null,
                titulo,
                numero,
                temporada,
                capaUrl,
                programa != null ? programa.toDomainSimple() : null,
                parte,
                duracao);
    }

    public Episodio toDomainSimple() {

        return Episodio.from(
                getId(),
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

    public String getCapaUrl() {
        return this.capaUrl;
    }

    public ArquivoEntity getArquivo() {
        return this.arquivo;
    }
}