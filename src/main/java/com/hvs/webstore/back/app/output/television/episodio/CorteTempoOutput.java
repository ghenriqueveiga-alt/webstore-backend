package com.hvs.webstore.back.app.output.television.episodio;

import java.time.LocalTime;

public record CorteTempoOutput(Long aId,
                               String aTipoCode,
                               String aTipoDesc,
                               String aDuracao,
                               LocalTime aInicio,
                               LocalTime aFim,
                               long aInicioSegundos,
                               long aFimSegundos,
                               Double aPercentualInicio,
                               Double aPercentualFim) {
}
