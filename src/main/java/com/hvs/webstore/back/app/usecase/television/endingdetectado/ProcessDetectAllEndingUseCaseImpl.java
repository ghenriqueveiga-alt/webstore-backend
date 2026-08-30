package com.hvs.webstore.back.app.usecase.television.endingdetectado;

import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectAllEndingCommand;
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
import com.hvs.webstore.back.domain.validation.notification.Erro;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessDetectAllEndingUseCaseImpl extends ProcessDetectAllEndingUseCase {

    private final EpisodioDomainGateway episodioGateway;
    private final EndingDetectadoDomainGateway endingDetectadoGateway;
    private final EndingDetector endingDetector;
    private final MediaPathResolver mediaPathResolver;

    public ProcessDetectAllEndingUseCaseImpl(final EpisodioDomainGateway episodioGateway,
                                             final EndingDetectadoDomainGateway endingDetectadoGateway,
                                             final EndingDetector endingDetector,
                                             final MediaPathResolver mediaPathResolver) {

        this.episodioGateway = episodioGateway;
        this.endingDetectadoGateway = endingDetectadoGateway;
        this.endingDetector = endingDetector;
        this.mediaPathResolver = mediaPathResolver;
    }

    @Override
    public Either<Notification, ProcessDetectAllEndingOutput> execute(final ProcessDetectAllEndingCommand aIn) {

        final List<Episodio> episodios = this.episodioGateway.readByPrograma(aIn.aProgramaId());
        if (episodios.isEmpty()) {
            return Either.left(Notification.create(new Erro("No episodes found for program with id: " + aIn.aProgramaId() + ".")));
        }
        return this.doProcess(episodios);
    }

    private Either<Notification, ProcessDetectAllEndingOutput> doProcess(final List<Episodio> episodios) {

        final List<String> caminhos = new ArrayList<>();
        for (Episodio ep : episodios) {
            final String caminho = this.mediaPathResolver.resolve(
                    ep.getArquivo() != null ? ep.getArquivo().getCaminho() : null);
            if (caminho == null || caminho.isBlank()) {
                continue;
            }
            caminhos.add(caminho);
        }
        final Map<String, EndingDetector.DetectedEnding> detectados = this.endingDetector.detectEnding(caminhos);

        final List<EndingDetectadoOutput> outputs = new ArrayList<>();
        for (Episodio ep : episodios) {
            final String caminho = this.mediaPathResolver.resolve(
                    ep.getArquivo() != null ? ep.getArquivo().getCaminho() : null);
            final Long arquivoId = ep.getArquivo() != null ? ((ArquivoId) ep.getArquivo().getId()).getValue() : null;
            final Long episodioId = ((EpisodioId) ep.getId()).getValue();
            if (caminho == null || caminho.isBlank()) {
                continue;
            }
            final EndingDetector.DetectedEnding d = detectados.getOrDefault(caminho,
                    new EndingDetector.DetectedEnding(caminho, 0L, 0.0, 0.0, false));
            this.endingDetectadoGateway.deleteByEpisodio(episodioId);
            final EndingDetectado criado = this.endingDetectadoGateway.create(EndingDetectado.create(episodioId, arquivoId,
                    d.aInicioSegundos(), d.aDuracaoSegundos(), d.aConfianca(), d.aDetectado()));
            outputs.add(EndingDetectadoOutput.from(criado, formatTime(criado.getInicio())));
        }
        return Either.right(new ProcessDetectAllEndingOutput(true, outputs));
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
