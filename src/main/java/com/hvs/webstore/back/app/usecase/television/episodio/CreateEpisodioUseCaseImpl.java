package com.hvs.webstore.back.app.usecase.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.CreateEpisodioCommand;
import com.hvs.webstore.back.app.output.television.episodio.CreateEpisodioOutput;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
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
                                             aIn.aProgramaId(),
                                             aIn.aCorteIds());
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
