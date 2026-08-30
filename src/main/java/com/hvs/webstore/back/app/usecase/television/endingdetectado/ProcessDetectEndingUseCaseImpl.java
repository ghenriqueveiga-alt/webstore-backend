package com.hvs.webstore.back.app.usecase.television.endingdetectado;

import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectEndingCommand;
import com.hvs.webstore.back.app.output.television.endingdetectado.EndingDetectadoOutput;
import com.hvs.webstore.back.app.output.television.endingdetectado.ProcessDetectadoEndingOutput;
import com.hvs.webstore.back.app.service.EndingDetector;
import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoId;
import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectado;
import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectadoDomainGateway;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioId;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaId;
import com.hvs.webstore.back.domain.validation.notification.Erro;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ProcessDetectEndingUseCaseImpl extends ProcessDetectEndingUseCase {

    private final EpisodioDomainGateway episodioGateway;
    private final EndingDetectadoDomainGateway endingDetectadoGateway;
    private final EndingDetector endingDetector;
    private final MediaPathResolver mediaPathResolver;

    public ProcessDetectEndingUseCaseImpl(final EpisodioDomainGateway episodioGateway,
                                          final EndingDetectadoDomainGateway endingDetectadoGateway,
                                          final EndingDetector endingDetector,
                                          final MediaPathResolver mediaPathResolver) {

        this.episodioGateway = episodioGateway;
        this.endingDetectadoGateway = endingDetectadoGateway;
        this.endingDetector = endingDetector;
        this.mediaPathResolver = mediaPathResolver;
    }

    @Override
    public Either<Notification, ProcessDetectadoEndingOutput> execute(final ProcessDetectEndingCommand aIn) {

        final Optional<Episodio> episodioDb = this.episodioGateway.read(EpisodioId.from(aIn.aEpisodioId()));
        if (episodioDb.isEmpty()) {
            return Either.left(Notification.create(new Erro("The Episode with id: " + aIn.aEpisodioId() + " could not be found.")));
        }
        final Episodio alvo = episodioDb.get();
        final String caminhoAlvoRelativo = alvo.getArquivo() != null ? alvo.getArquivo().getCaminho() : null;
        final String caminhoAlvo = this.mediaPathResolver.resolve(caminhoAlvoRelativo);
        final Long arquivoId = alvo.getArquivo() != null ? ((ArquivoId) alvo.getArquivo().getId()).getValue() : null;
        final Long programaId = alvo.getPrograma() != null ? ((ProgramaId) alvo.getPrograma().getId()).getValue() : null;

        final List<EndingDetectado> cached = this.endingDetectadoGateway.readByEpisodio(aIn.aEpisodioId());
        if (!cached.isEmpty()) {
            return Either.right(new ProcessDetectadoEndingOutput(true,
                    cached.stream().map(d -> EndingDetectadoOutput.from(d, formatTime(d.getInicio()))).toList()));
        }
        if (caminhoAlvo == null) {
            return Either.right(new ProcessDetectadoEndingOutput(true, List.of()));
        }

        final Path grupoAlvo = Paths.get(caminhoAlvo).getParent();
        final List<String> caminhos = new ArrayList<>();
        if (programaId != null) {
            for (Episodio ep : this.episodioGateway.readByPrograma(programaId)) {
                final String caminho = this.mediaPathResolver.resolve(
                        ep.getArquivo() != null ? ep.getArquivo().getCaminho() : null);
                if (caminho == null || caminho.isBlank()) {
                    continue;
                }
                final Path pai = Paths.get(caminho).getParent();
                if (!Objects.equals(grupoAlvo, pai)) {
                    continue;
                }
                caminhos.add(caminho);
            }
        }
        if (!caminhos.contains(caminhoAlvo)) {
            caminhos.add(caminhoAlvo);
        }

        final Map<String, EndingDetector.DetectedEnding> detectados = this.endingDetector.detectEnding(caminhos);
        final EndingDetector.DetectedEnding alvoDetectado = detectados.getOrDefault(caminhoAlvo,
                new EndingDetector.DetectedEnding(caminhoAlvo, 0L, 0.0, 0.0, false));

        this.endingDetectadoGateway.deleteByEpisodio(aIn.aEpisodioId());
        this.endingDetectadoGateway.create(EndingDetectado.create(aIn.aEpisodioId(), arquivoId,
                alvoDetectado.aInicioSegundos(), alvoDetectado.aDuracaoSegundos(),
                alvoDetectado.aConfianca(), alvoDetectado.aDetectado()));

        final List<EndingDetectadoOutput> endings = this.endingDetectadoGateway.readByEpisodio(aIn.aEpisodioId())
                .stream().map(d -> EndingDetectadoOutput.from(d, formatTime(d.getInicio()))).toList();

        return Either.right(new ProcessDetectadoEndingOutput(true, endings));
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
