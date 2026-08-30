package com.hvs.webstore.back.app.usecase.television.introdetectado;

import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectNaoDetectadosIntroCommand;
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
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProcessDetectNaoDetectadosIntroUseCaseImpl extends ProcessDetectNaoDetectadosIntroUseCase {

    private final EpisodioDomainGateway episodioGateway;
    private final IntroDetectadoDomainGateway introDetectadoGateway;
    private final IntroDetector introDetector;
    private final MediaPathResolver mediaPathResolver;

    public ProcessDetectNaoDetectadosIntroUseCaseImpl(final EpisodioDomainGateway episodioGateway,
                                                      final IntroDetectadoDomainGateway introDetectadoGateway,
                                                      final IntroDetector introDetector,
                                                      final MediaPathResolver mediaPathResolver) {

        this.episodioGateway = episodioGateway;
        this.introDetectadoGateway = introDetectadoGateway;
        this.introDetector = introDetector;
        this.mediaPathResolver = mediaPathResolver;
    }

    @Override
    public Either<Notification, ProcessDetectAllIntroOutput> execute(
            final ProcessDetectNaoDetectadosIntroCommand aIn) {

        final boolean filtrarTipo = aIn.aTipo() != null && !aIn.aTipo().isBlank();
        final Set<Long> naoDetectadosIds = new HashSet<>(filtrarTipo
                ? this.introDetectadoGateway.readEpisodiosNaoDetectadosPorTipo(aIn.aTipo())
                : this.introDetectadoGateway.readEpisodiosNaoDetectados());
        final List<Long> programasIds = filtrarTipo
                ? this.introDetectadoGateway.readProgramasComEpisodiosNaoDetectadosPorTipo(aIn.aTipo())
                : this.introDetectadoGateway.readProgramasComEpisodiosNaoDetectados();

        final List<IntroDetectadoOutput> outputs = new ArrayList<>();
        for (Long programaId : programasIds) {
            this.processarPrograma(programaId, naoDetectadosIds, outputs);
        }
        return Either.right(new ProcessDetectAllIntroOutput(true, outputs));
    }

    private void processarPrograma(final Long aProgramaId,
                                   final Set<Long> aNaoDetectadosIds,
                                   final List<IntroDetectadoOutput> aOutputs) {

        final List<Episodio> episodios = this.episodioGateway.readByPrograma(aProgramaId);
        final List<String> caminhos = new ArrayList<>();
        final Set<String> caminhosUnicos = new LinkedHashSet<>();
        for (Episodio ep : episodios) {
            final String caminho = this.mediaPathResolver.resolve(
                    ep.getArquivo() != null ? ep.getArquivo().getCaminho() : null);
            if (caminho == null || caminho.isBlank()) {
                continue;
            }
            if (caminhosUnicos.add(caminho)) {
                caminhos.add(caminho);
            }
        }

        final Map<String, IntroDetector.DetectedIntro> detectados =
                this.introDetector.detectIntro(caminhos);

        for (Episodio ep : episodios) {
            final Long episodioId = ((EpisodioId) ep.getId()).getValue();
            if (!aNaoDetectadosIds.contains(episodioId)) {
                continue;
            }
            final String caminho = this.mediaPathResolver.resolve(
                    ep.getArquivo() != null ? ep.getArquivo().getCaminho() : null);
            if (caminho == null || caminho.isBlank()) {
                continue;
            }
            final Long arquivoId = ((ArquivoId) ep.getArquivo().getId()).getValue();
            final IntroDetector.DetectedIntro d = detectados.getOrDefault(caminho,
                    new IntroDetector.DetectedIntro(caminho, 0L, 0.0, 0.0, false));
            this.introDetectadoGateway.deleteByEpisodio(episodioId);
            final IntroDetectado criado = this.introDetectadoGateway.create(IntroDetectado.create(episodioId, arquivoId,
                    d.aInicioSegundos(), d.aDuracaoSegundos(), d.aConfianca(), d.aDetectado()));
            aOutputs.add(IntroDetectadoOutput.from(criado, formatTime(criado.getInicio())));
        }
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