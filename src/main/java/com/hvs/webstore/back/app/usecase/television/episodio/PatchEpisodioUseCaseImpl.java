package com.hvs.webstore.back.app.usecase.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.PatchEpisodioCommand;
import com.hvs.webstore.back.app.output.television.episodio.PatchEpisodioOutput;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioId;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchEpisodioUseCaseImpl extends PatchEpisodioUseCase {

    private final EpisodioDomainGateway gateway;

    public PatchEpisodioUseCaseImpl(
            EpisodioDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchEpisodioOutput> execute(PatchEpisodioCommand aIn) {

        Optional<Episodio> episodioDb;

        if (aIn.aId() != null) {

            episodioDb = this.gateway.read(EpisodioId.from(aIn.aId()));
        } else {

            episodioDb = this.gateway.readByUuid(EpisodioUuid.from(aIn.aUuid()));
        }

        if (episodioDb.isPresent()) {

            final var notification = Notification.create();
            final var episodio = Episodio.patch(aIn.aStatusCode(),
                                                aIn.aArquivoId(),
                                                aIn.aTitulo(),
                                                aIn.aNumero(),
                                                aIn.aTemporada(),
                                                aIn.aCapaUrl(),
                                                aIn.aProgramaId(),
                                                aIn.aCorteIds(),
                                                aIn.aParte(),
                                                episodioDb.get());
            episodio.validate(notification);

            return notification.hasError() ? Left(notification) : patch(episodio);
        } else {

            String responseId;

            if (aIn.aId() != null) {

                responseId = String.valueOf(aIn.aId());
            } else {

                responseId = aIn.aUuid();
            }

            return Either.left(Notification
                    .create(new Error("The Episode with id: " + responseId + " could not be found.")));
        }
    }

    @Transactional
    private Either<Notification, PatchEpisodioOutput> patch(final Episodio aEpisodio){

        return Try(() -> this.gateway.patch(aEpisodio))
                .toEither()
                .bimap(Notification::create, PatchEpisodioOutput::from);
    }
}
