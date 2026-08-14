package com.hvs.webstore.back.infra.persistence.television.episodio;

import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.television.arquivo.ArquivoEntity;
import com.hvs.webstore.back.infra.persistence.television.corte.CorteEntity;
import com.hvs.webstore.back.infra.persistence.television.programa.ProgramaEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "episodio")
public class EpisodioEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @OneToOne
    @JoinColumn(name = "arquivo_id", referencedColumnName = "id")
    private ArquivoEntity arquivo;
    private String titulo;
    private Long numero;
    private Long temporada;
    private String capaUrl;

    @ManyToOne
    @JoinColumn(name = "programa_id")
    private ProgramaEntity programa;

    @OneToMany(mappedBy = "episodio")
    private List<CorteEntity> cortes;

    private Boolean processado;

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
                          final List<CorteEntity> cortes,
                          final Boolean processado) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.arquivo = arquivo;
        this.titulo = titulo;
        this.numero = numero;
        this.temporada = temporada;
        this.capaUrl = capaUrl;
        this.programa = programa;
        this.cortes = cortes;
        this.processado = processado;
    }

    public static EpisodioEntity from(final Episodio aEpisodio) {

        return new EpisodioEntity(
                aEpisodio.getId().getValue() < 0 ? null : aEpisodio.getId().getValue(),
                aEpisodio.getUuid().getValue(),
                aEpisodio.getStatusCode().getDesc(),
                aEpisodio.getArquivo() != null ? ArquivoEntity.from(aEpisodio.getArquivo()) : null,
                aEpisodio.getTitulo(),
                aEpisodio.getNumero(),
                aEpisodio.getTemporada(),
                aEpisodio.getCapaUrl(),
                aEpisodio.getPrograma() != null ? ProgramaEntity.from(aEpisodio.getPrograma()) : null,
                aEpisodio.getCortes() != null && !aEpisodio.getCortes().isEmpty() ?
                    aEpisodio.getCortes().stream().map(corte ->
                            CorteEntity.from(corte.getId().getValue())).toList() : null,
                aEpisodio.getProcessado());
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
                arquivo != null ? arquivo.toDomainChildren(): null,
                titulo,
                numero,
                temporada,
                capaUrl,
                programa != null ? programa.toDomainChildren() : null,
                cortes != null && !cortes.isEmpty() ?
                        cortes.stream().map(CorteEntity::toDomainChildren).toList() : null,
                processado);
    }

    public Episodio toDomainChildren() {

        return Episodio.from(
                getId(),
                uuid,
                statusDesc,
                arquivo != null ? arquivo.toDomainSimple(): null,
                titulo,
                numero,
                temporada,
                capaUrl,
                programa != null ? programa.toDomainSimple() : null,
                cortes != null && !cortes.isEmpty() ?
                        cortes.stream().map(CorteEntity::toDomainSimple).toList() : null,
                processado);
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
}
