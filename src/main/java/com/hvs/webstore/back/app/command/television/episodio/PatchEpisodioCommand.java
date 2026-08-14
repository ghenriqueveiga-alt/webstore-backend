package com.hvs.webstore.back.app.command.television.episodio;

import java.util.List;

public record PatchEpisodioCommand(Long aId,
                                   String aUuid,
                                   String aStatusDesc,
                                   Long aArquivoId,
                                   String aTitulo,
                                   Long aNumero,
                                   Long aTemporada,
                                   String aCapaUrl,
                                   Long aProgramaId,
                                   List<Long> aCorteIds) {

    public static PatchEpisodioCommand from(final Long aId,
                                            final PatchEpisodioCommand aInput) {

        return new PatchEpisodioCommand(
                aId,
                null,
                aInput.aStatusDesc,
                aInput.aArquivoId,
                aInput.aTitulo,
                aInput.aNumero,
                aInput.aTemporada,
                aInput.aCapaUrl,
                aInput.aProgramaId,
                aInput.aCorteIds);
    }

    public static PatchEpisodioCommand from(final String aUuid,
                                            final PatchEpisodioCommand aInput) {

        return new PatchEpisodioCommand(
                null,
                aUuid,
                aInput.aStatusDesc,
                aInput.aArquivoId,
                aInput.aTitulo,
                aInput.aNumero,
                aInput.aTemporada,
                aInput.aCapaUrl,
                aInput.aProgramaId,
                aInput.aCorteIds);
    }
}