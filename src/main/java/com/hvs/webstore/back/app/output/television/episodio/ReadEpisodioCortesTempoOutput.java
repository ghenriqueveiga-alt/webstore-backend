package com.hvs.webstore.back.app.output.television.episodio;

import java.util.List;

public record ReadEpisodioCortesTempoOutput(Long aId,
                                            String aTitulo,
                                            String aDuracaoArquivo,
                                            String aDuracaoRealVideo,
                                            String aOrigemDuracao,
                                            String aDuracaoTotalCortes,
                                            List<CorteTempoOutput> aCortes) {
}