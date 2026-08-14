package com.hvs.webstore.back.domain.entity.television.episodio;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;
import com.hvs.webstore.back.domain.entity.television.corte.Corte;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.List;
import java.util.Objects;

public class Episodio extends Entity<EpisodioId> {

    private final EpisodioUuid uuid;
    private final EpisodioStatus statusCode;
    private final Arquivo arquivo;
    private final String titulo;
    private final Long numero;
    private final Long temporada;
    private final String capaUrl;
    private final Programa programa;
    private final List<Corte> cortes;
    private final Boolean processado;

    private Episodio(final EpisodioId id,
                     final EpisodioUuid uuid,
                     final EpisodioStatus statusCode,
                     final Arquivo arquivo,
                     final String titulo,
                     final Long numero,
                     final Long temporada,
                     final String capaUrl,
                     final Programa programa,
                     final List<Corte> cortes,
                     final Boolean processado) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.arquivo = arquivo;
        this.titulo = titulo;
        this.numero = numero;
        this.temporada = temporada;
        this.capaUrl = capaUrl;
        this.programa = programa;
        this.cortes = cortes;
        this.processado = processado;
    }

    public static Episodio create(final Long aArquivoId,
                                  final String aTitulo,
                                  final Long aNumero,
                                  final Long aTemporada,
                                  final String aCapaUrl,
                                  final Long aProgramaId,
                                  final List<Long> aCorteIds) {

        return new Episodio(
                EpisodioId.from(-1L),
                EpisodioUuid.unique(),
                EpisodioStatus.ACTIVE,
                aArquivoId != null ? Arquivo.from(aArquivoId) : null,
                aTitulo,
                aNumero,
                aTemporada,
                aCapaUrl,
                aProgramaId != null ? Programa.from(aProgramaId) : null,
                aCorteIds != null && !aCorteIds.isEmpty() ?
                        aCorteIds.stream().map(Corte::from).toList() : null,
                false);
    }

    public static Episodio update(final Long aId,
                                  final String aUuid,
                                  final String aStatusCode,
                                  final Long aArquivoId,
                                  final String aTitulo,
                                  final Long aNumero,
                                  final Long aTemporada,
                                  final String aCapaUrl,
                                  final Long aProgramaId,
                                  final List<Long> aCorteIds) {

        return new Episodio(
                aId != null ? EpisodioId.from(aId) : null,
                aUuid != null ? EpisodioUuid.from(aUuid) : null,
                aStatusCode != null ? EpisodioStatus.findByCode(aStatusCode) : null,
                aArquivoId != null ? Arquivo.from(aArquivoId) : null,
                aTitulo,
                aNumero,
                aTemporada,
                aCapaUrl,
                aProgramaId != null ? Programa.from(aProgramaId) : null,
                aCorteIds != null && !aCorteIds.isEmpty() ?
                        aCorteIds.stream().map(Corte::from).toList() : null,
                false);
    }

    public static Episodio patch(final String aStatusCode,
                                 final Long aArquivoId,
                                 final String aTitulo,
                                 final Long aNumero,
                                 final Long aTemporada,
                                 final String aCapaUrl,
                                 final Long aProgramaId,
                                 final List<Long> aCorteIds,
                                 final Episodio aEpisodioDB) {

        return new Episodio(
                aEpisodioDB.getId(),
                aEpisodioDB.getUuid(),
                aStatusCode != null ? EpisodioStatus.findByCode(aStatusCode) : aEpisodioDB.getStatusCode(),
                aArquivoId != null ? Arquivo.from(aArquivoId) : aEpisodioDB.getArquivo(),
                aTitulo != null ? aTitulo : aEpisodioDB.getTitulo(),
                aNumero != null ? aNumero : aEpisodioDB.getNumero(),
                aTemporada != null ? aTemporada : aEpisodioDB.getTemporada(),
                aCapaUrl != null ? aCapaUrl : aEpisodioDB.getCapaUrl(),
                aProgramaId != null ? Programa.from(aProgramaId) : aEpisodioDB.getPrograma(),
                aCorteIds != null && !aCorteIds.isEmpty() ?
                        aCorteIds.stream().map(Corte::from).toList() : aEpisodioDB.getCortes(),
                aEpisodioDB.getProcessado());
    }

    public static Episodio from(final Long aId,
                                final String aUuid,
                                final String aStatusDesc,
                                final Arquivo aArquivo,
                                final String aTitulo,
                                final Long aNumero,
                                final Long aTemporada,
                                final String aCapaUrl,
                                final Programa aPrograma,
                                final List<Corte> aCortes,
                                final Boolean aProcessado) {

        return new Episodio(
                aId != null ? EpisodioId.from(aId) : null,
                aUuid != null ? EpisodioUuid.from(aUuid) : null,
                aStatusDesc != null ? EpisodioStatus.findByDesc(aStatusDesc) : null,
                aArquivo,
                aTitulo,
                aNumero,
                aTemporada,
                aCapaUrl,
                aPrograma,
                aCortes,
                aProcessado);
    }

    public static Episodio from(final Long aId) {

        return new Episodio(
                aId != null ? EpisodioId.from(aId) : null,
                null,
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

    public static Episodio from(final String aUuid) {

        return new Episodio(
                null,
                aUuid != null ? EpisodioUuid.from(aUuid) : null,
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

    public Episodio processar() {

        return new Episodio(
                this.getId(),
                this.getUuid(),
                this.getStatusCode(),
                this.getArquivo(),
                this.getTitulo(),
                this.getNumero(),
                this.getTemporada(),
                this.getCapaUrl(),
                this.getPrograma(),
                this.getCortes(),
                Boolean.TRUE);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new EpisodioValidator(aHandler, this).validate();
    }

    public EpisodioUuid getUuid() {
        return uuid;
    }
    public EpisodioStatus getStatusCode() {
        return statusCode;
    }
    public Arquivo getArquivo() {
        return arquivo;
    }
    public String getTitulo() {
        return titulo;
    }
    public Long getNumero() {
        return numero;
    }
    public Long getTemporada() {
        return temporada;
    }
    public String getCapaUrl() {
        return capaUrl;
    }
    public Programa getPrograma() {
        return programa;
    }
    public List<Corte> getCortes() {
        return cortes;
    }
    public Boolean getProcessado() {
        return processado;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Episodio episodio = (Episodio) o;

        return Objects.equals(uuid, episodio.uuid) &&
                statusCode == episodio.statusCode &&
                Objects.equals(arquivo, episodio.arquivo) &&
                Objects.equals(titulo, episodio.titulo) &&
                Objects.equals(numero, episodio.numero) &&
                Objects.equals(temporada, episodio.temporada) &&
                Objects.equals(capaUrl, episodio.capaUrl) &&
                Objects.equals(programa, episodio.programa) &&
                Objects.equals(cortes, episodio.cortes) &&
                Objects.equals(processado, episodio.processado);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                arquivo,
                titulo,
                numero,
                temporada,
                capaUrl,
                programa,
                cortes,
                processado);
    }
}
