package com.hvs.webstore.back.app.output.television.episodio;

public record CorteTempoOutput(Long aId,
                               String aTipoCode,
                               String aTipoDesc,
                               String aDuracao,
                               Long aInicioSegundos,
                               Long aFimSegundos,
                               String aInicio,
                               String aFim,
                               Double aPercentualInicio,
                               Double aPercentualFim) {
}