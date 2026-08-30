package com.hvs.webstore.back.app.usecase.television.endingdetectado;

import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectNaoDetectadosEndingCommand;
import com.hvs.webstore.back.app.output.television.endingdetectado.EndingDetectadoOutput;
import com.hvs.webstore.back.app.output.television.endingdetectado.ProcessDetectAllEndingOutput;
import com.hvs.webstore.back.app.service.EndingDetector;
import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoId;
import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectado;
import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectadoDomainGateway;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioId;
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

public class ProcessDetectNaoDetectadosEndingUseCaseImpl extends ProcessDetectNaoDetectadosEndingUseCase {

    private final EpisodioDomainGateway episodioGateway;
    private final EndingDetectadoDomainGateway endingDetectadoGateway;
    private final EndingDetector endingDetector;
    private final MediaPathResolver mediaPathResolver;

    public ProcessDetectNaoDetectadosEndingUseCaseImpl(final EpisodioDomainGateway episodioGateway,
                                                       final EndingDetectadoDomainGateway endingDetectadoGateway,
                                                       final EndingDetector endingDetector,
                                                       final MediaPathResolver mediaPathResolver) {

        this.episodioGateway = episodioGateway;
        this.endingDetectadoGateway = endingDetectadoGateway;
        this.endingDetector = endingDetector;
        this.mediaPathResolver = mediaPathResolver;
    }

    @Override
    public Either<Notification, ProcessDetectAllEndingOutput> execute(
            final ProcessDetectNaoDetectadosEndingCommand aIn) {

        final boolean filtrarTipo = aIn.aTipo() != null && !aIn.aTipo().isBlank();
        final Set<Long> naoDetectadosIds = new HashSet<>(filtrarTipo
                ? this.endingDetectadoGateway.readEpisodiosNaoDetectadosPorTipo(aIn.aTipo())
                : this.endingDetectadoGateway.readEpisodiosNaoDetectados());
        final List<Long> programasIds = filtrarTipo
                ? this.endingDetectadoGateway.readProgramasComEpisodiosNaoDetectadosPorTipo(aIn.aTipo())
                : this.endingDetectadoGateway.readProgramasComEpisodiosNaoDetectados();

        final List<EndingDetectadoOutput> outputs = new ArrayList<>();
        for (Long programaId : programasIds) {
            this.processarPrograma(programaId, naoDetectadosIds, outputs);
        }
        return Either.right(new ProcessDetectAllEndingOutput(true, outputs));
    }

    private void processarPrograma(final Long aProgramaId,
                                   final Set<Long> aNaoDetectadosIds,
                                   final List<EndingDetectadoOutput> aOutputs) {

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

        final Map<String, EndingDetector.DetectedEnding> detectados =
                this.endingDetector.detectEnding(caminhos);

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
            final EndingDetector.DetectedEnding d = detectados.getOrDefault(caminho,
                    new EndingDetector.DetectedEnding(caminho, 0L, 0.0, 0.0, false));
            this.endingDetectadoGateway.deleteByEpisodio(episodioId);
            final EndingDetectado criado = this.endingDetectadoGateway.create(EndingDetectado.create(episodioId, arquivoId,
                    d.aInicioSegundos(), d.aDuracaoSegundos(), d.aConfianca(), d.aDetectado()));
            aOutputs.add(EndingDetectadoOutput.from(criado, formatTime(criado.getInicio())));
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
