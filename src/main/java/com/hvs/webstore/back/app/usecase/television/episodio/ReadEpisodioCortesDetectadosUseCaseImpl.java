package com.hvs.webstore.back.app.usecase.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.ReadEpisodioCommand;
import com.hvs.webstore.back.app.output.television.episodio.CorteDetectadoOutput;
import com.hvs.webstore.back.app.output.television.episodio.ReadEpisodioCortesDetectadosOutput;
import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.app.service.VideoCutDetector;
import com.hvs.webstore.back.app.service.VideoDurationReader;
import com.hvs.webstore.back.domain.entity.television.corte.Corte;
import com.hvs.webstore.back.domain.entity.television.corte.CorteStatus;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioId;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReadEpisodioCortesDetectadosUseCaseImpl extends ReadEpisodioCortesDetectadosUseCase {

    private static final Map<String, Integer> TIPO_ORDER = Map.ofEntries(
            Map.entry("AB", 1),
            Map.entry("TI", 2),
            Map.entry("P1", 3),
            Map.entry("VI", 4),
            Map.entry("CO", 5),
            Map.entry("VV", 6),
            Map.entry("P2", 7),
            Map.entry("EN", 8),
            Map.entry("PP", 9),
            Map.entry("IN", 10),
            Map.entry("VC", 11),
            Map.entry("MI", 12));

    private final EpisodioDomainGateway gateway;
    private final VideoDurationReader videoDurationReader;
    private final VideoCutDetector videoCutDetector;
    private final MediaPathResolver mediaPathResolver;

    public ReadEpisodioCortesDetectadosUseCaseImpl(
            EpisodioDomainGateway gateway,
            VideoDurationReader videoDurationReader,
            VideoCutDetector videoCutDetector,
            MediaPathResolver mediaPathResolver) {
        this.gateway = gateway;
        this.videoDurationReader = videoDurationReader;
        this.videoCutDetector = videoCutDetector;
        this.mediaPathResolver = mediaPathResolver;
    }

    @Override
    public Either<Notification, ReadEpisodioCortesDetectadosOutput> execute(ReadEpisodioCommand aIn) {

        Optional<Episodio> episodioDb;

        if (aIn.aId() != null) {

            episodioDb = this.gateway.read(EpisodioId.from(aIn.aId()));
        } else {

            episodioDb = this.gateway.readByUuid(EpisodioUuid.from(aIn.aUuid()));
        }

        if (episodioDb.isEmpty()) {

            String responseId;
            if (aIn.aId() != null) {
                responseId = String.valueOf(aIn.aId());
            } else {
                responseId = aIn.aUuid();
            }

            return Either.left(Notification
                    .create(new Error("The Episode with id: " + responseId + " could not be found.")));
        }

        final Episodio episodio = episodioDb.get();

        final String caminhoArquivo = this.mediaPathResolver.resolve(
                episodio.getArquivo() != null ? episodio.getArquivo().getCaminho() : null);

        final long duracaoRealSegundos = caminhoArquivo != null
                ? this.videoDurationReader.readDurationSeconds(caminhoArquivo) : 0L;

        final long fileSeconds = duracaoRealSegundos > 0
                ? duracaoRealSegundos
                : toSeconds(episodio.getDuracao());

        final List<Corte> cortes = episodio.getCortes() != null
                ? episodio.getCortes().stream()
                        .filter(corte -> corte.getStatus() == CorteStatus.ACTIVE)
                        .filter(corte -> corte.getTipo() != null && corte.getDuracao() != null)
                        .sorted(Comparator.comparingInt(corte -> TIPO_ORDER.getOrDefault(corte.getTipo().getCode(), 99)))
                        .toList()
                : List.of();

        final long totalCortesSeconds = cortes.stream().mapToLong(corte -> toSeconds(corte.getDuracao())).sum();
        final var cortesOutput = new java.util.ArrayList<CorteDetectadoOutput>();
        long accumulatedSeconds = 0L;

        for (Corte corte : cortes) {

            final long corteSeconds = toSeconds(corte.getDuracao());
            final double inicioPercent = totalCortesSeconds > 0
                    ? (accumulatedSeconds * 100.0) / totalCortesSeconds : 0.0;
            final double fimPercent = totalCortesSeconds > 0
                    ? ((accumulatedSeconds + corteSeconds) * 100.0) / totalCortesSeconds : 100.0;

            final long inicioSeconds = fileSeconds > 0
                    ? Math.round((inicioPercent / 100.0) * fileSeconds) : accumulatedSeconds;
            final long fimSeconds = fileSeconds > 0
                    ? Math.round((fimPercent / 100.0) * fileSeconds) : accumulatedSeconds + corteSeconds;

            accumulatedSeconds += corteSeconds;

            cortesOutput.add(new CorteDetectadoOutput(
                    corte.getTipo().getCode(),
                    inicioSeconds,
                    fimSeconds,
                    formatSeconds(inicioSeconds),
                    formatSeconds(fimSeconds),
                    null,
                    null));
        }

        List<CorteDetectadoOutput> detectados = new java.util.ArrayList<>();

        if (caminhoArquivo != null) {

            for (VideoCutDetector.DetectedCommercial commercial : this.videoCutDetector.detectCommercials(caminhoArquivo)) {

                detectados.add(new CorteDetectadoOutput(
                        "SILENCIO",
                        commercial.aInicio(),
                        commercial.aFim(),
                        formatSeconds(commercial.aInicio()),
                        formatSeconds(commercial.aFim()),
                        null,
                        "Provável comercial/intervalo (silêncio de " + round1(commercial.aDuracaoSegundos()) + "s)"));
            }

            for (VideoCutDetector.DetectedScene scene : this.videoCutDetector.detectScenes(caminhoArquivo)) {

                detectados.add(new CorteDetectadoOutput(
                        "CENA",
                        scene.aTimestamp(),
                        scene.aTimestamp(),
                        formatSeconds(scene.aTimestamp()),
                        formatSeconds(scene.aTimestamp()),
                        round2(scene.aScore()),
                        "Mudança de cena (score " + round2(scene.aScore()) + ")"));
            }
        }

        return Either.right(new ReadEpisodioCortesDetectadosOutput(
                episodio.getId().getValue(),
                episodio.getTitulo(),
                fileSeconds,
                cortesOutput,
                detectados));
    }

    private static long toSeconds(final String aDuracao) {

        if (aDuracao == null || aDuracao.isBlank()) {
            return 0L;
        }

        final String[] parts = aDuracao.trim().split(":");
        try {
            if (parts.length == 3) {
                return Long.parseLong(parts[0]) * 3600L
                        + Long.parseLong(parts[1]) * 60L
                        + Long.parseLong(parts[2]);
            }
            if (parts.length == 2) {
                return Long.parseLong(parts[0]) * 60L + Long.parseLong(parts[1]);
            }
            return Long.parseLong(parts[0]);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    private static String formatSeconds(final long aSeconds) {

        final long h = aSeconds / 3600L;
        final long m = (aSeconds % 3600L) / 60L;
        final long s = aSeconds % 60L;

        return String.format("%02d:%02d:%02d", h, m, s);
    }

    private static Double round1(final double aValue) {

        return Math.round(aValue * 10.0) / 10.0;
    }

    private static Double round2(final double aValue) {

        return Math.round(aValue * 100.0) / 100.0;
    }
}