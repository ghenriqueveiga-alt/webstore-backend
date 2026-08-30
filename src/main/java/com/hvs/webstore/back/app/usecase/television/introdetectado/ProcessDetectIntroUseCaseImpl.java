package com.hvs.webstore.back.app.usecase.television.introdetectado;

import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectIntroCommand;
import com.hvs.webstore.back.app.output.television.introdetectado.IntroDetectadoOutput;
import com.hvs.webstore.back.app.output.television.introdetectado.ProcessDetectadoIntroOutput;
import com.hvs.webstore.back.app.service.IntroDetector;
import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoId;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioId;
import com.hvs.webstore.back.domain.entity.television.introdetectado.IntroDetectado;
import com.hvs.webstore.back.domain.entity.television.introdetectado.IntroDetectadoDomainGateway;
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

public class ProcessDetectIntroUseCaseImpl extends ProcessDetectIntroUseCase {

    private final EpisodioDomainGateway episodioGateway;
    private final IntroDetectadoDomainGateway introDetectadoGateway;
    private final IntroDetector introDetector;
    private final MediaPathResolver mediaPathResolver;

    public ProcessDetectIntroUseCaseImpl(final EpisodioDomainGateway episodioGateway,
                                         final IntroDetectadoDomainGateway introDetectadoGateway,
                                         final IntroDetector introDetector,
                                         final MediaPathResolver mediaPathResolver) {

        this.episodioGateway = episodioGateway;
        this.introDetectadoGateway = introDetectadoGateway;
        this.introDetector = introDetector;
        this.mediaPathResolver = mediaPathResolver;
    }

    @Override
    public Either<Notification, ProcessDetectadoIntroOutput> execute(final ProcessDetectIntroCommand aIn) {

        final Optional<Episodio> episodioDb = this.episodioGateway.read(EpisodioId.from(aIn.aEpisodioId()));
        if (episodioDb.isEmpty()) {
            return Either.left(Notification.create(new Erro("The Episode with id: " + aIn.aEpisodioId() + " could not be found.")));
        }
        final Episodio alvo = episodioDb.get();
        final String caminhoAlvoRelativo = alvo.getArquivo() != null ? alvo.getArquivo().getCaminho() : null;
        final String caminhoAlvo = this.mediaPathResolver.resolve(caminhoAlvoRelativo);
        final Long arquivoId = alvo.getArquivo() != null ? ((ArquivoId) alvo.getArquivo().getId()).getValue() : null;
        final Long programaId = alvo.getPrograma() != null ? ((ProgramaId) alvo.getPrograma().getId()).getValue() : null;

        final List<IntroDetectado> cached = this.introDetectadoGateway.readByEpisodio(aIn.aEpisodioId());
        if (!cached.isEmpty()) {
            return Either.right(new ProcessDetectadoIntroOutput(true,
                    cached.stream().map(d -> IntroDetectadoOutput.from(d, formatTime(d.getInicio()))).toList()));
        }
        if (caminhoAlvo == null) {
            return Either.right(new ProcessDetectadoIntroOutput(true, List.of()));
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

        final Map<String, IntroDetector.DetectedIntro> detectados = this.introDetector.detectIntro(caminhos);
        final IntroDetector.DetectedIntro alvoDetectado = detectados.getOrDefault(caminhoAlvo,
                new IntroDetector.DetectedIntro(caminhoAlvo, 0L, 0.0, 0.0, false));

        this.introDetectadoGateway.deleteByEpisodio(aIn.aEpisodioId());
        this.introDetectadoGateway.create(IntroDetectado.create(aIn.aEpisodioId(), arquivoId,
                alvoDetectado.aInicioSegundos(), alvoDetectado.aDuracaoSegundos(),
                alvoDetectado.aConfianca(), alvoDetectado.aDetectado()));

        final List<IntroDetectadoOutput> intros = this.introDetectadoGateway.readByEpisodio(aIn.aEpisodioId())
                .stream().map(d -> IntroDetectadoOutput.from(d, formatTime(d.getInicio()))).toList();

        return Either.right(new ProcessDetectadoIntroOutput(true, intros));
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