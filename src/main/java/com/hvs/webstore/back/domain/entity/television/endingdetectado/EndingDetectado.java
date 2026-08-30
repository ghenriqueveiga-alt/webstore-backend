package com.hvs.webstore.back.domain.entity.television.endingdetectado;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class EndingDetectado extends Entity<EndingDetectadoId> {

    public static final Instant INSTANT_BASE = Instant.EPOCH;

    private final EndingDetectadoUuid uuid;
    private final EndingDetectadoStatus status;
    private final Episodio episodio;
    private final Arquivo arquivo;
    private final Instant inicio;
    private final Instant duracao;
    private final Instant fim;
    private final Double confianca;
    private final Boolean detectado;

    private EndingDetectado(final EndingDetectadoId id,
                            final EndingDetectadoUuid uuid,
                            final EndingDetectadoStatus status,
                            final Episodio episodio,
                            final Arquivo arquivo,
                            final Instant inicio,
                            final Instant duracao,
                            final Instant fim,
                            final Double confianca,
                            final Boolean detectado) {

        super(id);
        this.uuid = uuid;
        this.status = status;
        this.episodio = episodio;
        this.arquivo = arquivo;
        this.inicio = inicio;
        this.duracao = duracao;
        this.fim = fim;
        this.confianca = confianca;
        this.detectado = detectado;
    }

    public static EndingDetectado create(final Long aEpisodioId,
                                         final Long aArquivoId,
                                         final Instant aInicio,
                                         final Double aDuracaoSegundos,
                                         final Double aConfianca,
                                         final Boolean aDetectado) {

        return new EndingDetectado(
                EndingDetectadoId.from(-1L),
                EndingDetectadoUuid.unique(),
                EndingDetectadoStatus.ACTIVE,
                aEpisodioId != null ? Episodio.from(aEpisodioId) : null,
                aArquivoId != null ? Arquivo.from(aArquivoId) : null,
                aInicio,
                durToInstant(aDuracaoSegundos),
                calcFim(aInicio, aDuracaoSegundos),
                aConfianca,
                aDetectado);
    }

    public static EndingDetectado create(final Long aEpisodioId,
                                         final Long aArquivoId,
                                         final Long aInicioSegundos,
                                         final Double aDuracaoSegundos,
                                         final Double aConfianca,
                                         final Boolean aDetectado) {

        return create(aEpisodioId, aArquivoId,
                aInicioSegundos != null ? INSTANT_BASE.plusSeconds(aInicioSegundos) : null,
                aDuracaoSegundos, aConfianca, aDetectado);
    }

    public static EndingDetectado from(final Long aId,
                                       final String aUuid,
                                       final String aStatusDesc,
                                       final Episodio aEpisodio,
                                       final Arquivo aArquivo,
                                       final Instant aInicio,
                                       final Instant aDuracao,
                                       final Instant aFim,
                                       final Double aConfianca,
                                       final Boolean aDetectado) {

        return new EndingDetectado(
                aId != null ? EndingDetectadoId.from(aId) : null,
                aUuid != null ? EndingDetectadoUuid.from(aUuid) : null,
                aStatusDesc != null ? EndingDetectadoStatus.findByDesc(aStatusDesc) : null,
                aEpisodio,
                aArquivo,
                aInicio,
                aDuracao,
                aFim,
                aConfianca,
                aDetectado);
    }

    private static Instant durToInstant(final Double aSegundos) {

        if (aSegundos == null) {
            return null;
        }
        return INSTANT_BASE.plusMillis(Math.round(aSegundos * 1000.0));
    }

    private static Instant calcFim(final Instant aInicio, final Double aDuracaoSegundos) {

        if (aInicio == null || aDuracaoSegundos == null) {
            return null;
        }
        return INSTANT_BASE.plusMillis(Math.round((aInicio.getEpochSecond() + aDuracaoSegundos) * 1000.0));
    }

    @Override
    public void validate(final ValidationHandler aHandler) {

        new EndingDetectadoValidator(aHandler, this).validate();
    }

    public EndingDetectadoUuid getUuid() {
        return this.uuid;
    }
    public EndingDetectadoStatus getStatus() {
        return this.status;
    }
    public Episodio getEpisodio() {
        return this.episodio;
    }
    public Arquivo getArquivo() {
        return this.arquivo;
    }
    public Instant getInicio() {
        return this.inicio;
    }
    public Instant getDuracao() {
        return this.duracao;
    }
    public Instant getFim() {
        return this.fim;
    }
    public Double getConfianca() {
        return this.confianca;
    }
    public Boolean getDetectado() {
        return this.detectado;
    }

    @Override
    public boolean equals(final Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        EndingDetectado that = (EndingDetectado) o;

        return Objects.equals(this.uuid, that.uuid) &&
                this.status == that.status &&
                Objects.equals(this.episodio, that.episodio) &&
                Objects.equals(this.arquivo, that.arquivo) &&
                Objects.equals(this.inicio, that.inicio) &&
                Objects.equals(this.duracao, that.duracao) &&
                Objects.equals(this.fim, that.fim) &&
                Objects.equals(this.confianca, that.confianca) &&
                Objects.equals(this.detectado, that.detectado);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                status,
                episodio,
                arquivo,
                inicio,
                duracao,
                fim,
                confianca,
                detectado);
    }
}
