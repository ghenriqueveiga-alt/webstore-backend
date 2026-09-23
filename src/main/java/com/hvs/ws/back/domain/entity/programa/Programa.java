package com.hvs.ws.back.domain.entity.programa;

import com.hvs.ws.back.domain.entity.Entity;
import com.hvs.ws.back.domain.entity.bloco.Bloco;
import com.hvs.ws.back.domain.entity.episodio.Episodio;
import com.hvs.ws.back.domain.entity.genero.Genero;
import com.hvs.ws.back.domain.validation.ValidationHandler;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

public class Programa extends Entity<ProgramaId> {

    private final ProgramaUuid uuid;
    private final ProgramaStatus status;
    private final String nome;
    private final Boolean emProducao;
    private final ProgramaTipo tipo;
    private final Long temporadas;
    private final Long partes;
    private final List<Episodio> episodios;
    private final LocalDateTime lancamento;
    private final LocalDateTime encerramento;
    private final List<Bloco> blocos;
    private final String sinopse;
    private final ClassificacaoEtaria classificacaoEtaria;
    private final String estudio;
    private final String capaUrl;
    private final String redeOriginal;
    private final TipoExibicao tipoExibicao;
    private final String tituloAlternativo;
    private final String audioIdiomas;
    private final String legendasDisponiveis;
    private final String siteOficial;
    private final List<Genero> generos;

    private Programa(final ProgramaId id,
                     final ProgramaUuid uuid,
                     final ProgramaStatus status,
                     final String nome,
                     final Boolean emProducao,
                     final ProgramaTipo tipo,
                     final Long temporadas,
                     final Long partes,
                     final List<Episodio> episodios,
                     final LocalDateTime lancamento,
                     final LocalDateTime encerramento,
                     final List<Bloco> blocos,
                     final String sinopse,
                     final ClassificacaoEtaria classificacaoEtaria,
                     final String estudio,
                     final String capaUrl,
                     final String redeOriginal,
                     final TipoExibicao tipoExibicao,
                     final String tituloAlternativo,
                     final String audioIdiomas,
                     final String legendasDisponiveis,
                     final String siteOficial,
                     final List<Genero> generos) {

        super(id);
        this.uuid = uuid;
        this.status = status;
        this.nome = nome;
        this.emProducao = emProducao;
        this.tipo = tipo;
        this.temporadas = temporadas;
        this.partes = partes;
        this.episodios = episodios;
        this.lancamento = lancamento;
        this.encerramento = encerramento;
        this.blocos = blocos;
        this.sinopse = sinopse;
        this.classificacaoEtaria = classificacaoEtaria;
        this.estudio = estudio;
        this.capaUrl = capaUrl;
        this.redeOriginal = redeOriginal;
        this.tipoExibicao = tipoExibicao;
        this.tituloAlternativo = tituloAlternativo;
        this.audioIdiomas = audioIdiomas;
        this.legendasDisponiveis = legendasDisponiveis;
        this.siteOficial = siteOficial;
        this.generos = generos;
    }

    public static Programa create(final String aNome,
                                  final Boolean aEmProducao,
                                  final String aTipoCode,
                                  final Long aTemporadas,
                                  final Long aPartes,
                                  final List<Long> aEpisodioIds,
                                  final String aLancamento,
                                  final String aEncerramento,
                                  final List<Long> aBlocoIds,
                                  final String aSinopse,
                                  final String aClassificacaoEtariaCode,
                                  final String aEstudio,
                                  final String aCapaUrl,
                                  final String aRedeOriginal,
                                  final String aTipoExibicaoCode,
                                  final String aTituloAlternativo,
                                  final String aAudioIdiomas,
                                  final String aLegendasDisponiveis,
                                  final String aSiteOficial,
                                  final List<Long> aGeneroIds) {

        return new Programa(
                ProgramaId.from(-1L),
                ProgramaUuid.unique(),
                ProgramaStatus.ACTIVE,
                aNome,
                aEmProducao,
                aTipoCode != null ? ProgramaTipo.findByCode(aTipoCode) : null,
                aTemporadas,
                aPartes,
                aEpisodioIds != null && !aEpisodioIds.isEmpty() ?
                        aEpisodioIds.stream().map(Episodio::from).toList() : null,
                aLancamento != null ? LocalDateTime.parse(aLancamento) : null,
                aEncerramento != null ? LocalDateTime.parse(aEncerramento) : null,
                aBlocoIds != null && !aBlocoIds.isEmpty() ?
                        aBlocoIds.stream().map(Bloco::from).toList() : null,
                aSinopse,
                aClassificacaoEtariaCode != null ? ClassificacaoEtaria.findByCode(aClassificacaoEtariaCode) : null,
                aEstudio,
                aCapaUrl,
                aRedeOriginal,
                aTipoExibicaoCode != null ? TipoExibicao.findByCode(aTipoExibicaoCode) : null,
                aTituloAlternativo,
                aAudioIdiomas,
                aLegendasDisponiveis,
                aSiteOficial,
                aGeneroIds != null && !aGeneroIds.isEmpty() ?
                        aGeneroIds.stream().map(Genero::from).toList() : null);
    }

    public static Programa update(final Long aId,
                                  final String aUuid,
                                  final String aStatusCode,
                                  final String aNome,
                                  final Boolean aEmProducao,
                                  final String aTipoCode,
                                  final Long aTemporadas,
                                  final Long aPartes,
                                  final List<Long> aEpisodioIds,
                                  final String aLancamento,
                                  final String aEncerramento,
                                  final List<Long> aBlocoIds,
                                  final String aSinopse,
                                  final String aClassificacaoEtariaCode,
                                  final String aEstudio,
                                  final String aCapaUrl,
                                  final String aRedeOriginal,
                                  final String aTipoExibicaoCode,
                                  final String aTituloAlternativo,
                                  final String aAudioIdiomas,
                                  final String aLegendasDisponiveis,
                                  final String aSiteOficial,
                                  final List<Long> aGeneroIds) {

        return new Programa(
                aId != null ? ProgramaId.from(aId) : null,
                aUuid != null ? ProgramaUuid.from(aUuid) : null,
                aStatusCode != null ? ProgramaStatus.findByCode(aStatusCode) : null,
                aNome,
                aEmProducao,
                aTipoCode != null ? ProgramaTipo.findByCode(aTipoCode) : null,
                aTemporadas,
                aPartes,
                aEpisodioIds != null && !aEpisodioIds.isEmpty() ?
                        aEpisodioIds.stream().map(Episodio::from).toList() : null,
                aLancamento != null ? LocalDateTime.parse(aLancamento) : null,
                aEncerramento != null ? LocalDateTime.parse(aEncerramento) : null,
                aBlocoIds != null && !aBlocoIds.isEmpty() ?
                        aBlocoIds.stream().map(Bloco::from).toList() : null,
                aSinopse,
                aClassificacaoEtariaCode != null ? ClassificacaoEtaria.findByCode(aClassificacaoEtariaCode) : null,
                aEstudio,
                aCapaUrl,
                aRedeOriginal,
                aTipoExibicaoCode != null ? TipoExibicao.findByCode(aTipoExibicaoCode) : null,
                aTituloAlternativo,
                aAudioIdiomas,
                aLegendasDisponiveis,
                aSiteOficial,
                aGeneroIds != null && !aGeneroIds.isEmpty() ?
                        aGeneroIds.stream().map(Genero::from).toList() : null);
    }

    public static Programa patch(final String aStatusCode,
                                 final String aNome,
                                 final Boolean aEmProducao,
                                 final String aTipoCode,
                                 final Long aTemporadas,
                                 final Long aPartes,
                                 final List<Long> aEpisodioIds,
                                 final String aLancamento,
                                 final String aEncerramento,
                                 final List<Long> aBlocoIds,
                                 final String aSinopse,
                                 final String aClassificacaoEtariaCode,
                                 final String aEstudio,
                                 final String aCapaUrl,
                                 final String aRedeOriginal,
                                 final String aTipoExibicaoCode,
                                 final String aTituloAlternativo,
                                 final String aAudioIdiomas,
                                 final String aLegendasDisponiveis,
                                 final String aSiteOficial,
                                 final List<Long> aGeneroIds,
                                 final Programa aProgramaDB) {

        return new Programa(
                aProgramaDB.getId(),
                aProgramaDB.getUuid(),
                aStatusCode != null ? ProgramaStatus.findByCode(aStatusCode) : aProgramaDB.getStatus(),
                aNome != null ? aNome : aProgramaDB.getNome(),
                aEmProducao != null ? aEmProducao : aProgramaDB.getEmProducao(),
                aTipoCode != null ? ProgramaTipo.findByCode(aTipoCode) : aProgramaDB.getTipo(),
                aTemporadas != null ? aTemporadas : aProgramaDB.getTemporadas(),
                aPartes != null ? aPartes : aProgramaDB.getPartes(),
                aEpisodioIds != null && !aEpisodioIds.isEmpty() ?
                        aEpisodioIds.stream().map(Episodio::from).toList() : aProgramaDB.getEpisodios(),
                aLancamento != null ? LocalDateTime.parse(aLancamento) : aProgramaDB.getLancamento(),
                aEncerramento != null ? LocalDateTime.parse(aEncerramento) : aProgramaDB.getEncerramento(),
                aBlocoIds != null && !aBlocoIds.isEmpty() ?
                        aBlocoIds.stream().map(Bloco::from).toList() : aProgramaDB.getBlocos(),
                aSinopse != null ? aSinopse : aProgramaDB.getSinopse(),
                aClassificacaoEtariaCode != null ? ClassificacaoEtaria.findByCode(aClassificacaoEtariaCode) : aProgramaDB.getClassificacaoEtaria(),
                aEstudio != null ? aEstudio : aProgramaDB.getEstudio(),
                aCapaUrl != null ? aCapaUrl : aProgramaDB.getCapaUrl(),
                aRedeOriginal != null ? aRedeOriginal : aProgramaDB.getRedeOriginal(),
                aTipoExibicaoCode != null ? TipoExibicao.findByCode(aTipoExibicaoCode) : aProgramaDB.getTipoExibicao(),
                aTituloAlternativo != null ? aTituloAlternativo : aProgramaDB.getTituloAlternativo(),
                aAudioIdiomas != null ? aAudioIdiomas : aProgramaDB.getAudioIdiomas(),
                aLegendasDisponiveis != null ? aLegendasDisponiveis : aProgramaDB.getLegendasDisponiveis(),
                aSiteOficial != null ? aSiteOficial : aProgramaDB.getSiteOficial(),
                aGeneroIds != null && !aGeneroIds.isEmpty() ?
                        aGeneroIds.stream().map(Genero::from).toList() : aProgramaDB.getGeneros());
    }

    public static Programa from(final Long aId,
                                final String aUuid,
                                final String aStatusDesc,
                                final String aNome,
                                final Boolean aEmProducao,
                                final String aTipoDesc,
                                final Long aTemporadas,
                                final Long aPartes,
                                final List<Episodio> aEpisodios,
                                final String aLancamento,
                                final String aEncerramento,
                                final List<Bloco> aBlocos,
                                final String aSinopse,
                                final String aClassificacaoEtariaDesc,
                                final String aEstudio,
                                final String aCapaUrl,
                                final String aRedeOriginal,
                                final String aTipoExibicaoDesc,
                                final String aTituloAlternativo,
                                final String aAudioIdiomas,
                                final String aLegendasDisponiveis,
                                final String aSiteOficial,
                                final List<Genero> aGeneros) {

        return new Programa(
                aId != null ? ProgramaId.from(aId) : null,
                aUuid != null ? ProgramaUuid.from(aUuid) : null,
                aStatusDesc != null ? ProgramaStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aEmProducao,
                aTipoDesc != null ? ProgramaTipo.findByDesc(aTipoDesc) : null,
                aTemporadas,
                aPartes,
                aEpisodios,
                aLancamento != null ? parseDate(aLancamento) : null,
                aEncerramento != null ? parseDate(aEncerramento) : null,
                aBlocos,
                aSinopse,
                aClassificacaoEtariaDesc != null ? ClassificacaoEtaria.findByDesc(aClassificacaoEtariaDesc) : null,
                aEstudio,
                aCapaUrl,
                aRedeOriginal,
                aTipoExibicaoDesc != null ? TipoExibicao.findByDesc(aTipoExibicaoDesc) : null,
                aTituloAlternativo,
                aAudioIdiomas,
                aLegendasDisponiveis,
                aSiteOficial,
                aGeneros);
    }

    public static Programa from(final Long aId) {

        return new Programa(
                aId != null ? ProgramaId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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

    public static Programa from(final String aUuid) {

        return new Programa(
                null,
                aUuid != null ? ProgramaUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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

    @Override
    public void validate(ValidationHandler aHandler) {

        new ProgramaValidator(aHandler, this).validate();
    }

    private static LocalDateTime parseDate(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return LocalDateTime.parse(value);
        } catch (DateTimeParseException ignored) {}
        try {
            return LocalDateTime.parse(value + "-01-01T00:00:00");
        } catch (DateTimeParseException ignored) {}
        try {
            return LocalDateTime.of(Integer.parseInt(value.trim()), Month.JANUARY, 1, 0, 0, 0);
        } catch (Exception ignored) {}
        return null;
    }

    public ProgramaUuid getUuid() {
        return uuid;
    }
    public ProgramaStatus getStatus() {
        return status;
    }
    public String getNome() {
        return nome;
    }
    public Boolean getEmProducao() {
        return emProducao;
    }
    public ProgramaTipo getTipo() {
        return tipo;
    }
    public Long getTemporadas() {
        return temporadas;
    }
    public Long getPartes() {
        return partes;
    }
    public List<Episodio> getEpisodios() {
        return episodios;
    }
    public LocalDateTime getLancamento() {
        return lancamento;
    }
    public LocalDateTime getEncerramento() {
        return encerramento;
    }
    public List<Bloco> getBlocos() {
        return blocos;
    }
    public String getSinopse() {
        return sinopse;
    }
    public ClassificacaoEtaria getClassificacaoEtaria() {
        return classificacaoEtaria;
    }
    public String getEstudio() {
        return estudio;
    }
    public String getCapaUrl() {
        return capaUrl;
    }
    public String getRedeOriginal() {
        return redeOriginal;
    }
    public TipoExibicao getTipoExibicao() {
        return tipoExibicao;
    }
    public String getTituloAlternativo() {
        return tituloAlternativo;
    }
    public String getAudioIdiomas() {
        return audioIdiomas;
    }
    public String getLegendasDisponiveis() {
        return legendasDisponiveis;
    }
    public String getSiteOficial() {
        return siteOficial;
    }
    public List<Genero> getGeneros() {
        return generos;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Programa programa = (Programa) o;

        return Objects.equals(uuid, programa.uuid) &&
                status == programa.status &&
                Objects.equals(nome, programa.nome) &&
                Objects.equals(emProducao, programa.emProducao) &&
                tipo == programa.tipo &&
                Objects.equals(temporadas, programa.temporadas) &&
                Objects.equals(partes, programa.partes) &&
                Objects.equals(episodios, programa.episodios) &&
                Objects.equals(lancamento, programa.lancamento) &&
                Objects.equals(encerramento, programa.encerramento) &&
                Objects.equals(blocos, programa.blocos) &&
                Objects.equals(sinopse, programa.sinopse) &&
                classificacaoEtaria == programa.classificacaoEtaria &&
                Objects.equals(estudio, programa.estudio) &&
                Objects.equals(capaUrl, programa.capaUrl) &&
                Objects.equals(redeOriginal, programa.redeOriginal) &&
                tipoExibicao == programa.tipoExibicao &&
                Objects.equals(tituloAlternativo, programa.tituloAlternativo) &&
                Objects.equals(audioIdiomas, programa.audioIdiomas) &&
                Objects.equals(legendasDisponiveis, programa.legendasDisponiveis) &&
                Objects.equals(siteOficial, programa.siteOficial) &&
                Objects.equals(generos, programa.generos);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                status,
                nome,
                emProducao,
                tipo,
                temporadas,
                partes,
                episodios,
                lancamento,
                encerramento,
                blocos,
                sinopse,
                classificacaoEtaria,
                estudio,
                capaUrl,
                redeOriginal,
                tipoExibicao,
                tituloAlternativo,
                audioIdiomas,
                legendasDisponiveis,
                siteOficial,
                generos);
    }
}
