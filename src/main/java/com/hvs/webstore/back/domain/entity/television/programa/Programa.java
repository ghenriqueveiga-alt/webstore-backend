package com.hvs.webstore.back.domain.entity.television.programa;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.television.bloco.Bloco;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Programa extends Entity<ProgramaId> {

    private final ProgramaUuid uuid;
    private final ProgramaStatus statusCode;
    private final String nome;
    private final Boolean emProducao;
    private final ProgramaTipo tipo;
    private final Long temporadas;
    private final List<Episodio> episodios;
    private final LocalDateTime lancamento;
    private final LocalDateTime encerramento;
    private final List<Bloco> blocos;

    private Programa(final ProgramaId id,
                     final ProgramaUuid uuid,
                     final ProgramaStatus statusCode,
                     final String nome,
                     final Boolean emProducao,
                     final ProgramaTipo tipo,
                     final Long temporadas,
                     final List<Episodio> episodios,
                     final LocalDateTime lancamento,
                     final LocalDateTime encerramento,
                     final List<Bloco> blocos) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nome = nome;
        this.emProducao = emProducao;
        this.tipo = tipo;
        this.temporadas = temporadas;
        this.episodios = episodios;
        this.lancamento = lancamento;
        this.encerramento = encerramento;
        this.blocos = blocos;
    }

    public static Programa create(final String aNome,
                                  final Boolean aEmProducao,
                                  final String aTipoDesc,
                                  final Long aTemporadas,
                                  final List<Long> aEpisodioIds,
                                  final String aLancamento,
                                  final String aEncerramento,
                                  final List<Long> aBlocoIds) {

        return new Programa(
                ProgramaId.from(-1L),
                ProgramaUuid.unique(),
                ProgramaStatus.ACTIVE,
                aNome,
                aEmProducao,
                aTipoDesc != null ? ProgramaTipo.findByDesc(aTipoDesc) : null,
                aTemporadas,
                aEpisodioIds != null && !aEpisodioIds.isEmpty() ?
                        aEpisodioIds.stream().map(Episodio::from).toList() : null,
                aLancamento != null ? LocalDateTime.parse(aLancamento) : null,
                aEncerramento != null ? LocalDateTime.parse(aEncerramento) : null,
                aBlocoIds != null && !aBlocoIds.isEmpty() ?
                        aBlocoIds.stream().map(Bloco::from).toList() : null);
    }

    public static Programa update(final Long aId,
                                  final String aUuid,
                                  final String aStatusCode,
                                  final String aNome,
                                  final Boolean aEmProducao,
                                  final String aTipoDesc,
                                  final Long aTemporadas,
                                  final List<Long> aEpisodioIds,
                                  final String aLancamento,
                                  final String aEncerramento,
                                  final List<Long> aBlocoIds) {

        return new Programa(
                aId != null ? ProgramaId.from(aId) : null,
                aUuid != null ? ProgramaUuid.from(aUuid) : null,
                aStatusCode != null ? ProgramaStatus.findByCode(aStatusCode) : null,
                aNome,
                aEmProducao,
                aTipoDesc != null ? ProgramaTipo.findByCode(aTipoDesc) : null,
                aTemporadas,
                aEpisodioIds != null && !aEpisodioIds.isEmpty() ?
                        aEpisodioIds.stream().map(Episodio::from).toList() : null,
                aLancamento != null ? LocalDateTime.parse(aLancamento) : null,
                aEncerramento != null ? LocalDateTime.parse(aEncerramento) : null,
                aBlocoIds != null && !aBlocoIds.isEmpty() ?
                        aBlocoIds.stream().map(Bloco::from).toList() : null);
    }

    public static Programa patch(final String aStatusCode,
                                 final String aNome,
                                 final Boolean aEmProducao,
                                 final String aTipoDesc,
                                 final Long aTemporadas,
                                 final List<Long> aEpisodioIds,
                                 final String aLancamento,
                                 final String aEncerramento,
                                 final List<Long> aBlocoIds,
                                 final Programa aExisting) {

        return new Programa(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? ProgramaStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aNome != null ? aNome : aExisting.getNome(),
                aEmProducao != null ? aEmProducao : aExisting.getEmProducao(),
                aTipoDesc != null ? ProgramaTipo.findByCode(aTipoDesc) : aExisting.getTipo(),
                aTemporadas != null ? aTemporadas : aExisting.getTemporadas(),
                aEpisodioIds != null && !aEpisodioIds.isEmpty() ?
                        aEpisodioIds.stream().map(Episodio::from).toList() : aExisting.getEpisodios(),
                aLancamento != null ? LocalDateTime.parse(aLancamento) : aExisting.getLancamento(),
                aEncerramento != null ? LocalDateTime.parse(aEncerramento) : aExisting.getEncerramento(),
                aBlocoIds != null && !aBlocoIds.isEmpty() ?
                        aBlocoIds.stream().map(Bloco::from).toList() : aExisting.getBlocos());
    }

    public static Programa from(final Long aId,
                                final String aUuid,
                                final String aStatusDesc,
                                final String aNome,
                                final Boolean aEmProducao,
                                final String aTipoDesc,
                                final Long aTemporadas,
                                final List<Episodio> aEpisodios,
                                final String aLancamento,
                                final String aEncerramento,
                                final List<Bloco> aBlocos) {

        return new Programa(
                aId != null ? ProgramaId.from(aId) : null,
                aUuid != null ? ProgramaUuid.from(aUuid) : null,
                aStatusDesc != null ? ProgramaStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aEmProducao,
                aTipoDesc != null ? ProgramaTipo.findByDesc(aTipoDesc) : null,
                aTemporadas,
                aEpisodios,
                aLancamento != null ? LocalDateTime.parse(aLancamento) : null,
                aEncerramento != null ? LocalDateTime.parse(aEncerramento) : null,
                aBlocos);
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
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new ProgramaValidator(aHandler, this).validate();
    }

    public ProgramaUuid getUuid() {
        return uuid;
    }
    public ProgramaStatus getStatusCode() {
        return statusCode;
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

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Programa programa = (Programa) o;

        return Objects.equals(uuid, programa.uuid) &&
                statusCode == programa.statusCode &&
                Objects.equals(nome, programa.nome) &&
                Objects.equals(emProducao, programa.emProducao) &&
                tipo == programa.tipo &&
                Objects.equals(temporadas, programa.temporadas) &&
                Objects.equals(episodios, programa.episodios) &&
                Objects.equals(lancamento, programa.lancamento) &&
                Objects.equals(encerramento, programa.encerramento) &&
                Objects.equals(blocos, programa.blocos);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nome,
                emProducao,
                tipo,
                temporadas,
                episodios,
                lancamento,
                encerramento,
                blocos);
    }
}
