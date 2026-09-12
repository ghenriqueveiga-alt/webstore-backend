package com.hvs.webstore.back.app.output.television.episodio;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Cortes e detecções do episódio")
public record ReadEpisodioCortesDetectadosOutput(Long aId,
                                                 String aTitulo,
                                                 Long aDuracaoRealSegundos,
                                                 List<CorteDetectadoOutput> aCortes,
                                                 List<CorteDetectadoOutput> aDetectados) {
}