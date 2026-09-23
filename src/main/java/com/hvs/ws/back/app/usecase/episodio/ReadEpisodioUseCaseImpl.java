package com.hvs.ws.back.app.usecase.episodio;

import com.hvs.ws.back.app.command.episodio.ReadEpisodioCommand;
import com.hvs.ws.back.app.output.episodio.ReadEpisodioOutput;
import com.hvs.ws.back.domain.entity.episodio.Episodio;
import com.hvs.ws.back.domain.entity.episodio.EpisodioDomainGateway;
import com.hvs.ws.back.domain.entity.episodio.EpisodioId;
import com.hvs.ws.back.domain.entity.episodio.EpisodioUuid;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadEpisodioUseCaseImpl extends ReadEpisodioUseCase {

    private final EpisodioDomainGateway gateway;

    public ReadEpisodioUseCaseImpl(
            EpisodioDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadEpisodioOutput> execute(ReadEpisodioCommand aIn) {

        Optional<Episodio> episodioDb;

        if (aIn.aId() != null) {

            episodioDb = this.gateway.read(EpisodioId.from(aIn.aId()));
        } else {

            episodioDb = this.gateway.readByUuid(EpisodioUuid.from(aIn.aUuid()));
        }

        if (episodioDb.isPresent()) {

            return Try(episodioDb::get).toEither().bimap(Notification::create, ReadEpisodioOutput::from);
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
}
