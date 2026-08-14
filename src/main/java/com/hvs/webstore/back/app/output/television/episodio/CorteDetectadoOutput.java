package com.hvs.webstore.back.app.output.television.episodio;

public record CorteDetectadoOutput(String aTipo,
                                   Long aInicio,
                                   Long aFim,
                                   String aInicioFormatado,
                                   String aFimFormatado,
                                   Double aScore,
                                   String aSugestao) {
}