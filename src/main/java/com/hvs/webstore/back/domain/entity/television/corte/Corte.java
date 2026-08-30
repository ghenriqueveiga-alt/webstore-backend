package com.hvs.webstore.back.domain.entity.television.corte;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.LocalTime;
import java.util.Objects;

public class Corte extends Entity<CorteId> {

    private final CorteUuid uuid;
    private final CorteStatus status;
    private final Arquivo arquivo;
    private final CorteTipo tipo;
    private final String duracao;
    private final Episodio episodio;
    private final LocalTime inicio;
    private final LocalTime fim;

    private Corte(final CorteId id,
                  final CorteUuid uuid,
                  final CorteStatus status,
                  final Arquivo arquivo,
                  final CorteTipo tipo,
                  final String duracao,
                  final Episodio episodio,
                  final LocalTime inicio,
                  final LocalTime fim) {

        super(id);
        this.uuid = uuid;
        this.status = status;
        this.arquivo = arquivo;
        this.tipo = tipo;
        this.duracao = duracao;
        this.episodio = episodio;
        this.inicio = inicio;
        this.fim = fim;
    }

    public static Corte create(final Long aArquivoId,
                               final String aTipoCode,
                               final String aDuracao,
                               final Long aEpisodioId,
                               final LocalTime aInicio,
                               final LocalTime aFim) {

        return new Corte(
                CorteId.from(-1L),
                CorteUuid.unique(),
                CorteStatus.ACTIVE,
                aArquivoId != null ? Arquivo.from(aArquivoId) : null,
                aTipoCode != null ? CorteTipo.findByCode(aTipoCode) : null,
                aDuracao,
                aEpisodioId != null ? Episodio.from(aEpisodioId) : null,
                aInicio,
                aFim);
    }

    public static Corte update(final Long aId,
                                final String aUuid,
                                final String aStatusCode,
                                final Long aArquivoId,
                                final String aTipoCode,
                                final String aDuracao,
                                final Long aEpisodioId,
                                final LocalTime aInicio,
                                final LocalTime aFim) {

        return new Corte(
                aId != null ? CorteId.from(aId) : null,
                aUuid != null ? CorteUuid.from(aUuid) : null,
                aStatusCode != null ? CorteStatus.findByCode(aStatusCode) : null,
                aArquivoId != null ? Arquivo.from(aArquivoId) : null,
                aTipoCode != null ? CorteTipo.findByCode(aTipoCode) : null,
                aDuracao,
                aEpisodioId != null ? Episodio.from(aEpisodioId) : null,
                aInicio,
                aFim);
    }

    public static Corte patch(final String aStatusCode,
                              final Long aArquivoId,
                              final String aTipoCode,
                              final String aDuracao,
                              final Long aEpisodioId,
                              final LocalTime aInicio,
                              final LocalTime aFim,
                              final Corte aCorteDB) {

        return new Corte(
                aCorteDB.getId(),
                aCorteDB.getUuid(),
                aStatusCode != null ? CorteStatus.findByCode(aStatusCode) : aCorteDB.getStatus(),
                aArquivoId != null ? Arquivo.from(aArquivoId) : aCorteDB.getArquivo(),
                aTipoCode != null ? CorteTipo.findByCode(aTipoCode) : aCorteDB.getTipo(),
                aDuracao != null ? aDuracao : aCorteDB.getDuracao(),
                aEpisodioId != null ? Episodio.from(aEpisodioId) : aCorteDB.getEpisodio(),
                aInicio != null ? aInicio : aCorteDB.getInicio(),
                aFim != null ? aFim : aCorteDB.getFim());
    }

    public static Corte from(final Long aId,
                             final String aUuid,
                             final String aStatusDesc,
                             final Arquivo aArquivo,
                             final String aTipoDesc,
                             final String aDuracao,
                             final Episodio aEpisodio,
                             final LocalTime aInicio,
                             final LocalTime aFim) {

        return new Corte(
                aId != null ? CorteId.from(aId) : null,
                aUuid != null ? CorteUuid.from(aUuid) : null,
                aStatusDesc != null ? CorteStatus.findByDesc(aStatusDesc) : null,
                aArquivo,
                aTipoDesc != null ? CorteTipo.findByDesc(aTipoDesc) : null,
                aDuracao,
                aEpisodio,
                aInicio,
                aFim);
    }

    public static Corte from(final Long aId) {

        return new Corte(
                aId != null ? CorteId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Corte from(final String aUuid) {

        return new Corte(
                null,
                aUuid != null ? CorteUuid.from(aUuid) : null,
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

        new CorteValidator(aHandler, this).validate();
    }

    public CorteUuid getUuid() {
        return uuid;
    }
    public CorteStatus getStatus() {
        return status;
    }
    public Arquivo getArquivo() {
        return arquivo;
    }
    public CorteTipo getTipo() {
        return tipo;
    }
    public String getDuracao() {
        return duracao;
    }
    public Episodio getEpisodio() {
        return episodio;
    }
    public LocalTime getInicio() {
        return inicio;
    }
    public LocalTime getFim() {
        return fim;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Corte corte = (Corte) o;

        return Objects.equals(uuid, corte.uuid) &&
                status == corte.status &&
                Objects.equals(arquivo, corte.arquivo) &&
                tipo == corte.tipo &&
                Objects.equals(duracao, corte.duracao) &&
                Objects.equals(episodio, corte.episodio) &&
                Objects.equals(inicio, corte.inicio) &&
                Objects.equals(fim, corte.fim);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                status,
                arquivo,
                tipo,
                duracao,
                episodio,
                inicio,
                fim);
    }
}
