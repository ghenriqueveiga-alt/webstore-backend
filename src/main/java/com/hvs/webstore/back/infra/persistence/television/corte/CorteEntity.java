package com.hvs.webstore.back.infra.persistence.television.corte;

import com.hvs.webstore.back.domain.entity.television.corte.Corte;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.television.arquivo.ArquivoEntity;
import com.hvs.webstore.back.infra.persistence.television.episodio.EpisodioEntity;
import jakarta.persistence.*;

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
    private String tipoCode;
    private String duracao;

    @ManyToOne
    @JoinColumn(name = "episodio_id")
    private EpisodioEntity episodio;

    public CorteEntity() {

    }

    public CorteEntity(final Long id,
                       final String uuid,
                       final String statusDesc,
                       final ArquivoEntity arquivo,
                       final String tipoCode,
                       final String duracao,
                       final EpisodioEntity episodio) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.arquivo = arquivo;
        this.tipoCode = tipoCode;
        this.duracao = duracao;
        this.episodio =episodio;
    }

    public static CorteEntity from(final Corte aCorte) {

        return new CorteEntity(
                aCorte.getId().getValue() < 0 ? null : aCorte.getId().getValue(),
                aCorte.getUuid().getValue(),
                aCorte.getStatusCode().getDesc(),
                aCorte.getArquivo() != null ? ArquivoEntity.from(aCorte.getArquivo().getId().getValue()) : null,
                aCorte.getTipo().getCode(),
                aCorte.getDuracao(),
                aCorte.getEpisodio() != null ? EpisodioEntity.from(aCorte.getEpisodio().getId().getValue()) : null);
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
                tipoCode,
                duracao,
                episodio != null ? episodio.toDomainChildren(): null);
    }

    public Corte toDomainChildren() {

        return Corte.from(
                getId(),
                uuid,
                statusDesc,
                arquivo != null ? arquivo.toDomainSimple(): null,
                tipoCode,
                duracao,
                episodio != null ? episodio.toDomainSimple(): null);
    }

    public Corte toDomainSimple() {

        return Corte.from(
                getId(),
                uuid,
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
