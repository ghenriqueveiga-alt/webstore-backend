package com.hvs.ws.back.app.command.episodio;

import java.util.List;

public record PatchEpisodioCommand(Long aId,
                                   String aUuid,
                                   String aStatusCode,
                                   Long aArquivoId,
                                   String aTitulo,
                                   Long aNumero,
                                   Long aTemporada,
                                   String aCapaUrl,
                                   Long aProgramaId,
                                   Long aParte,
                                   List<Long> aCorteIds) {

    public static PatchEpisodioCommand from(final Long aId,
                                            final PatchEpisodioCommand aInput) {

        return new PatchEpisodioCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aArquivoId,
                aInput.aTitulo,
                aInput.aNumero,
                aInput.aTemporada,
                aInput.aCapaUrl,
                aInput.aProgramaId,
                aInput.aParte,
                aInput.aCorteIds);
    }

    public static PatchEpisodioCommand from(final String aUuid,
                                            final PatchEpisodioCommand aInput) {

        return new PatchEpisodioCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aArquivoId,
                aInput.aTitulo,
                aInput.aNumero,
                aInput.aTemporada,
                aInput.aCapaUrl,
                aInput.aProgramaId,
                aInput.aParte,
                aInput.aCorteIds);
    }
}