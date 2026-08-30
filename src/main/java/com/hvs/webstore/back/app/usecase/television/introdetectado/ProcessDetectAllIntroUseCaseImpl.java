package com.hvs.webstore.back.app.usecase.television.introdetectado;

import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectAllIntroCommand;
import com.hvs.webstore.back.app.output.television.introdetectado.IntroDetectadoOutput;
import com.hvs.webstore.back.app.output.television.introdetectado.ProcessDetectAllIntroOutput;
import com.hvs.webstore.back.app.service.IntroDetector;
import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoId;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioId;
import com.hvs.webstore.back.domain.entity.television.introdetectado.IntroDetectado;
import com.hvs.webstore.back.domain.entity.television.introdetectado.IntroDetectadoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Erro;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessDetectAllIntroUseCaseImpl extends ProcessDetectAllIntroUseCase {

    private final EpisodioDomainGateway episodioGateway;
    private final IntroDetectadoDomainGateway introDetectadoGateway;
    private final IntroDetector introDetector;
    private final MediaPathResolver mediaPathResolver;

    public ProcessDetectAllIntroUseCaseImpl(final EpisodioDomainGateway episodioGateway,
                                            final IntroDetectadoDomainGateway introDetectadoGateway,
                                            final IntroDetector introDetector,
                                            final MediaPathResolver mediaPathResolver) {

        this.episodioGateway = episodioGateway;
        this.introDetectadoGateway = introDetectadoGateway;
        this.introDetector = introDetector;
        this.mediaPathResolver = mediaPathResolver;
    }

    @Override
    public Either<Notification, ProcessDetectAllIntroOutput> execute(final ProcessDetectAllIntroCommand aIn) {

        final List<Episodio> episodios = this.episodioGateway.readByPrograma(aIn.aProgramaId());
        if (episodios.isEmpty()) {
            return Either.left(Notification.create(new Erro("No episodes found for program with id: " + aIn.aProgramaId() + ".")));
        }
        return this.doProcess(episodios);
    }

    private Either<Notification, ProcessDetectAllIntroOutput> doProcess(final List<Episodio> episodios) {

        final List<String> caminhos = new ArrayList<>();
        for (Episodio ep : episodios) {
            final String caminho = this.mediaPathResolver.resolve(
                    ep.getArquivo() != null ? ep.getArquivo().getCaminho() : null);
            if (caminho == null || caminho.isBlank()) {
                continue;
            }
            caminhos.add(caminho);
        }
        final Map<String, IntroDetector.DetectedIntro> detectados = this.introDetector.detectIntro(caminhos);

        final List<IntroDetectadoOutput> outputs = new ArrayList<>();
        for (Episodio ep : episodios) {
            final String caminho = this.mediaPathResolver.resolve(
                    ep.getArquivo() != null ? ep.getArquivo().getCaminho() : null);
            final Long arquivoId = ep.getArquivo() != null ? ((ArquivoId) ep.getArquivo().getId()).getValue() : null;
            final Long episodioId = ((EpisodioId) ep.getId()).getValue();
            if (caminho == null || caminho.isBlank()) {
                continue;
            }
            final IntroDetector.DetectedIntro d = detectados.getOrDefault(caminho,
                    new IntroDetector.DetectedIntro(caminho, 0L, 0.0, 0.0, false));
            this.introDetectadoGateway.deleteByEpisodio(episodioId);
            final IntroDetectado criado = this.introDetectadoGateway.create(IntroDetectado.create(episodioId, arquivoId,
                    d.aInicioSegundos(), d.aDuracaoSegundos(), d.aConfianca(), d.aDetectado()));
            outputs.add(IntroDetectadoOutput.from(criado, formatTime(criado.getInicio())));
        }
        return Either.right(new ProcessDetectAllIntroOutput(true, outputs));
    }

    private static String formatTime(final Instant aTime) {

        if (aTime == null) {
            return "00:00:00";
        }
        final ZonedDateTime utc = aTime.atZone(ZoneOffset.UTC);
        return String.format("%02d:%02d:%02d",
                utc.getHour(), utc.getMinute(), utc.getSecond());
    }
}