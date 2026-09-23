package com.hvs.ws.back.app.output.episodio;

public record CorteDetectadoOutput(String aTipo,
                                   Long aInicio,
                                   Long aFim,
                                   String aInicioFormatado,
                                   String aFimFormatado,
                                   Double aScore,
                                   String aSugestao) {
}