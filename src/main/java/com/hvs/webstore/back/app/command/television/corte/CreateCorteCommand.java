package com.hvs.webstore.back.app.command.television.corte;

import java.time.LocalTime;

public record CreateCorteCommand(Long aArquivoId,
                                 String aTipoCode,
                                 String aDuracao,
                                 Long aEpisodioId,
                                 LocalTime aInicio,
                                 LocalTime aFim) {

    public static CreateCorteCommand from(final Long aArquivoId,
                                          final String aTipoCode,
                                          final String duracao,
                                          final Long aEpisodioId,
                                          final LocalTime aInicio,
                                          final LocalTime aFim) {

        return new CreateCorteCommand(
                aArquivoId,
                aTipoCode,
                duracao,
                aEpisodioId,
                aInicio,
                aFim);
    }
}
