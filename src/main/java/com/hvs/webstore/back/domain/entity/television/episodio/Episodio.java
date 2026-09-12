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
    private final EpisodioStatus status;
    private final Arquivo arquivo;
    private final String titulo;
    private final Long numero;
    private final Long temporada;
    private final String capaUrl;
    private final Programa programa;
    private final List<Corte> cortes;
    private final Integer parte;
    private final String duracao;

private Episodio(final EpisodioId id,
                 final EpisodioUuid uuid,
                 final EpisodioStatus status,
                 final Arquivo arquivo,
                 final String titulo,
                 final Long numero,
                 final Long temporada,
                 final String capaUrl,
                 final Programa programa,
                 final List<Corte> cortes,
                 final Integer parte,
                 final String duracao) {

        super(id);
        this.uuid = uuid;
        this.status = status;
        this.arquivo = arquivo;
        this.titulo = titulo;
        this.numero = numero;
        this.temporada = temporada;
        this.capaUrl = capaUrl;
        this.programa = programa;
        this.cortes = cortes;
        this.parte = parte;
        this.duracao = duracao;
    }

    public static Episodio create(final Long aArquivoId,
                                  final String aTitulo,
                                  final Long aNumero,
                                  final Long aTemporada,
                                  final String aCapaUrl,
                                  final Long aProgramaId,
                                  final List<Long> aCorteIds,
                                  final Integer parte) {

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
                aCorteIds != null && !aCorteIds.isEmpty()
                        ? aCorteIds.stream().map(Corte::from).toList() : null,
                parte,
                null);
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
                                  final List<Long> aCorteIds,
                                  final Integer parte) {

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
                aCorteIds != null && !aCorteIds.isEmpty()
                        ? aCorteIds.stream().map(Corte::from).toList() : null,
                parte,
                null);
    }

    public static Episodio patch(final String aStatusCode,
                                 final Long aArquivoId,
                                 final String aTitulo,
                                 final Long aNumero,
                                 final Long aTemporada,
                                 final String aCapaUrl,
                                 final Long aProgramaId,
                                 final List<Long> aCorteIds,
                                 final Integer parte,
                                 final Episodio aEpisodioDB) {

        return new Episodio(
                aEpisodioDB.getId(),
                aEpisodioDB.getUuid(),
                aStatusCode != null ? EpisodioStatus.findByCode(aStatusCode) : aEpisodioDB.getStatus(),
                aArquivoId != null ? Arquivo.from(aArquivoId) : aEpisodioDB.getArquivo(),
                aTitulo != null ? aTitulo : aEpisodioDB.getTitulo(),
                aNumero != null ? aNumero : aEpisodioDB.getNumero(),
                aTemporada != null ? aTemporada : aEpisodioDB.getTemporada(),
                aCapaUrl != null ? aCapaUrl : aEpisodioDB.getCapaUrl(),
                aProgramaId != null ? Programa.from(aProgramaId) : aEpisodioDB.getPrograma(),
                aCorteIds != null && !aCorteIds.isEmpty()
                        ? aCorteIds.stream().map(Corte::from).toList() : aEpisodioDB.getCortes(),
                parte < 0 ? parte : aEpisodioDB.getParte(),
                aEpisodioDB.getDuracao());
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
                                final Integer parte,
                                final String aDuracao) {

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
                parte,
                aDuracao);
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
                0,
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
                0,
                null);
    }

    public Episodio processar() {

        return new Episodio(
                this.getId(),
                this.getUuid(),
                this.getStatus(),
                this.getArquivo(),
                this.getTitulo(),
                this.getNumero(),
                this.getTemporada(),
                this.getCapaUrl(),
                this.getPrograma(),
                this.getCortes(),
                this.getParte(),
                this.getDuracao());
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new EpisodioValidator(aHandler, this).validate();
    }

    public EpisodioUuid getUuid() {
        return uuid;
    }
    public EpisodioStatus getStatus() {
        return status;
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

    public int getParte() {
        return parte;
    }

    public String getDuracao() {
        return duracao;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Episodio episodio = (Episodio) o;

        return Objects.equals(uuid, episodio.uuid) &&
                status == episodio.status &&
                Objects.equals(arquivo, episodio.arquivo) &&
                Objects.equals(titulo, episodio.titulo) &&
                Objects.equals(numero, episodio.numero) &&
                Objects.equals(temporada, episodio.temporada) &&
                Objects.equals(capaUrl, episodio.capaUrl) &&
                Objects.equals(programa, episodio.programa) &&
                Objects.equals(cortes, episodio.cortes) &&
                Objects.equals(parte, episodio.parte) &&
                Objects.equals(duracao, episodio.duracao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                status,
                arquivo,
                titulo,
                numero,
                temporada,
                capaUrl,
                programa,
                cortes,
                parte,
                duracao);
    }
}
