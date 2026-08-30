package com.hvs.webstore.back.infra.persistence.television.endingdetectado;

import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoId;
import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectado;
import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectadoId;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioId;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.television.arquivo.ArquivoEntity;
import com.hvs.webstore.back.infra.persistence.television.episodio.EpisodioEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "ending_detectado")
public class EndingDetectadoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "episodio_id")
    private EpisodioEntity episodio;

    @ManyToOne
    @JoinColumn(name = "arquivo_id")
    private ArquivoEntity arquivo;

    @Column(name = "inicio", columnDefinition = "datetime(6)")
    private Instant inicio;
    @Column(name = "duracao", columnDefinition = "datetime(6)")
    private Instant duracao;
    @Column(name = "fim", columnDefinition = "datetime(6)")
    private Instant fim;
    private Double confianca;
    private Boolean detectado;

    public EndingDetectadoEntity() {

    }

    public EndingDetectadoEntity(final Long id,
                                 final String uuid,
                                 final String statusDesc,
                                 final EpisodioEntity episodio,
                                 final ArquivoEntity arquivo,
                                 final Instant inicio,
                                 final Instant duracao,
                                 final Instant fim,
                                 final Double confianca,
                                 final Boolean detectado) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.episodio = episodio;
        this.arquivo = arquivo;
        this.inicio = inicio;
        this.duracao = duracao;
        this.fim = fim;
        this.confianca = confianca;
        this.detectado = detectado;
    }

    public static EndingDetectadoEntity from(final EndingDetectado aEndingDetectado) {

        return new EndingDetectadoEntity(
                aEndingDetectado.getId().getValue() < 0L ? null : aEndingDetectado.getId().getValue(),
                aEndingDetectado.getUuid().getValue(),
                aEndingDetectado.getStatus().getDesc(),
                aEndingDetectado.getEpisodio() != null
                        ? EpisodioEntity.from(((EpisodioId) aEndingDetectado.getEpisodio().getId()).getValue()) : null,
                aEndingDetectado.getArquivo() != null
                        ? ArquivoEntity.from(((ArquivoId) aEndingDetectado.getArquivo().getId()).getValue()) : null,
                aEndingDetectado.getInicio(),
                aEndingDetectado.getDuracao(),
                aEndingDetectado.getFim(),
                aEndingDetectado.getConfianca(),
                aEndingDetectado.getDetectado());
    }

    public EndingDetectado toDomain() {

        return EndingDetectado.from(
                getId(),
                uuid,
                statusDesc,
                episodio != null ? episodio.toDomainSimple() : null,
                arquivo != null ? arquivo.toDomainSimple() : null,
                inicio,
                duracao,
                fim,
                confianca,
                detectado);
    }

    @Override
    public Long getId() {
        return this.id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
