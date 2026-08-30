package com.hvs.webstore.back.app.output.television.endingdetectado;

import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectado;
import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectadoId;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioId;

import java.time.Instant;

public record EndingDetectadoOutput(Long aId,
                                    Long aEpisodioId,
                                    Instant aInicio,
                                    String aInicioFormatado,
                                    Instant aDuracao,
                                    Instant aFim,
                                    Double aConfianca,
                                    Boolean aDetectado) {

    public static EndingDetectadoOutput from(final EndingDetectado aEndingDetectado,
                                             final String aInicioFormatado) {

        return new EndingDetectadoOutput(
                ((EndingDetectadoId) aEndingDetectado.getId()).getValue(),
                aEndingDetectado.getEpisodio() != null
                        ? ((EpisodioId) aEndingDetectado.getEpisodio().getId()).getValue() : null,
                aEndingDetectado.getInicio(),
                aInicioFormatado,
                aEndingDetectado.getDuracao(),
                aEndingDetectado.getFim(),
                aEndingDetectado.getConfianca(),
                aEndingDetectado.getDetectado());
    }
}
