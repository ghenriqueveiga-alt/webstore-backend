package com.hvs.webstore.back.app.output.television.introdetectado;

import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioId;
import com.hvs.webstore.back.domain.entity.television.introdetectado.IntroDetectado;
import com.hvs.webstore.back.domain.entity.television.introdetectado.IntroDetectadoId;

import java.time.Instant;

public record IntroDetectadoOutput(Long aId,
                                   Long aEpisodioId,
                                   Instant aInicio,
                                   String aInicioFormatado,
                                   Instant aDuracao,
                                   Instant aFim,
                                   Double aConfianca,
                                   Boolean aDetectado) {

    public static IntroDetectadoOutput from(final IntroDetectado aIntroDetectado,
                                            final String aInicioFormatado) {

        return new IntroDetectadoOutput(
                ((IntroDetectadoId) aIntroDetectado.getId()).getValue(),
                aIntroDetectado.getEpisodio() != null
                        ? ((EpisodioId) aIntroDetectado.getEpisodio().getId()).getValue() : null,
                aIntroDetectado.getInicio(),
                aInicioFormatado,
                aIntroDetectado.getDuracao(),
                aIntroDetectado.getFim(),
                aIntroDetectado.getConfianca(),
                aIntroDetectado.getDetectado());
    }
}
