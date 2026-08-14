package com.hvs.webstore.back.domain.entity.television.corte;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Corte extends Entity<CorteId> {

    private final CorteUuid uuid;
    private final CorteStatus statusCode;
    private final Arquivo arquivo;
    private final CorteTipo tipo;
    private final String duracao;
    private final Episodio episodio;

    private Corte(final CorteId id,
                  final CorteUuid uuid,
                  final CorteStatus statusCode,
                  final Arquivo arquivo,
                  final CorteTipo tipo,
                  final String duracao,
                  final Episodio episodio) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.arquivo = arquivo;
        this.tipo = tipo;
        this.duracao = duracao;
        this.episodio = episodio;
    }

    public static Corte create(final Long aArquivoId,
                               final String aTipoCode,
                               final String aDuracao,
                               final Long aEpisodioId) {

        return new Corte(
                CorteId.from(-1L),
                CorteUuid.unique(),
                CorteStatus.ACTIVE,
                aArquivoId != null ? Arquivo.from(aArquivoId) : null,
                aTipoCode != null ? CorteTipo.findByCode(aTipoCode) : null,
                aDuracao,
                aEpisodioId != null ? Episodio.from(aEpisodioId) : null);
    }

    public static Corte update(final Long aId,
                                final String aUuid,
                                final String aStatusCode,
                                final Long aArquivoId,
                                final String aTipoCode,
                                final String aDuracao,
                                final Long aEpisodioId) {

        return new Corte(
                aId != null ? CorteId.from(aId) : null,
                aUuid != null ? CorteUuid.from(aUuid) : null,
                aStatusCode != null ? CorteStatus.findByCode(aStatusCode) : null,
                aArquivoId != null ? Arquivo.from(aArquivoId) : null,
                aTipoCode != null ? CorteTipo.findByCode(aTipoCode) : null,
                aDuracao,
                aEpisodioId != null ? Episodio.from(aEpisodioId) : null);
    }

    public static Corte patch(final String aStatusCode,
                              final Long aArquivoId,
                              final String aTipoCode,
                              final String aDuracao,
                              final Long aEpisodioId,
                              final Corte aCorteDB) {

        return new Corte(
                aCorteDB.getId(),
                aCorteDB.getUuid(),
                aStatusCode != null ? CorteStatus.findByCode(aStatusCode) : aCorteDB.getStatusCode(),
                aArquivoId != null ? Arquivo.from(aArquivoId) : aCorteDB.getArquivo(),
                aTipoCode != null ? CorteTipo.findByCode(aTipoCode) : aCorteDB.getTipo(),
                aDuracao != null ? aDuracao : aCorteDB.getDuracao(),
                aEpisodioId != null ? Episodio.from(aEpisodioId) : aCorteDB.getEpisodio());
    }

    public static Corte from(final Long aId,
                              final String aUuid,
                              final String aStatusDesc,
                              final Arquivo aArquivo,
                              final String aTipoDesc,
                              final String aDuracao,
                              final Episodio aEpisodio) {

        return new Corte(
                aId != null ? CorteId.from(aId) : null,
                aUuid != null ? CorteUuid.from(aUuid) : null,
                aStatusDesc != null ? CorteStatus.findByDesc(aStatusDesc) : null,
                aArquivo,
                aTipoDesc != null ? CorteTipo.findByDesc(aTipoDesc) : null,
                aDuracao,
                aEpisodio);
    }

    public static Corte from(final Long aId) {

        return new Corte(
                aId != null ? CorteId.from(aId) : null,
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
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new CorteValidator(aHandler, this).validate();
    }

    public CorteUuid getUuid() {
        return uuid;
    }
    public CorteStatus getStatusCode() {
        return statusCode;
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

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Corte corte = (Corte) o;

        return Objects.equals(uuid, corte.uuid) &&
                statusCode == corte.statusCode &&
                Objects.equals(arquivo, corte.arquivo) &&
                tipo == corte.tipo &&
                Objects.equals(duracao, corte.duracao) &&
                Objects.equals(episodio, corte.episodio);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                arquivo,
                tipo,
                duracao,
                episodio);
    }
}
