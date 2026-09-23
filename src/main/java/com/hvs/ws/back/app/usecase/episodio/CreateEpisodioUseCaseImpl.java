package com.hvs.ws.back.app.usecase.episodio;

import com.hvs.ws.back.app.command.episodio.CreateEpisodioCommand;
import com.hvs.ws.back.app.output.episodio.CreateEpisodioOutput;
import com.hvs.ws.back.domain.entity.episodio.Episodio;
import com.hvs.ws.back.domain.entity.episodio.EpisodioDomainGateway;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateEpisodioUseCaseImpl extends CreateEpisodioUseCase {

    private final EpisodioDomainGateway gateway;

    public CreateEpisodioUseCaseImpl(
            EpisodioDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateEpisodioOutput> execute(CreateEpisodioCommand aIn) {

        final var notification = Notification.create();
        final var episodio = Episodio.create(aIn.aArquivoId(),
                                             aIn.aTitulo(),
                                             aIn.aNumero(),
                                             aIn.aTemporada(),
                                             aIn.aCapaUrl(),
                                             aIn.aProgramaId(),
                                             aIn.parte());
        episodio.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(episodio);
    }

    @Transactional
    private Either<Notification, CreateEpisodioOutput> create(final Episodio aEpisodio){

        return Try(() -> this.gateway.create(aEpisodio))
                .toEither()
                .bimap(Notification::create, CreateEpisodioOutput::from);
    }
}
