package com.hvs.webstore.back.app.command.television.corte;

public record PatchCorteCommand(Long aId,
                                String aUuid,
                                String aStatusDesc,
                                Long aArquivoId,
                                String aTipoDesc,
                                String aDuracao,
                                Long aEpisodioId) {

    public static PatchCorteCommand from(final Long aId,
                                         final PatchCorteCommand aInput) {

        return new PatchCorteCommand(
                aId,
                null,
                aInput.aStatusDesc,
                aInput.aArquivoId,
                aInput.aTipoDesc,
                aInput.aDuracao,
                aInput.aEpisodioId);
    }
    public static PatchCorteCommand from(final String aUuid,
                                         final PatchCorteCommand aInput) {

        return new PatchCorteCommand(
                null,
                aUuid,
                aInput.aStatusDesc,
                aInput.aArquivoId,
                aInput.aTipoDesc,
                aInput.aDuracao,
                aInput.aEpisodioId);
    }
}