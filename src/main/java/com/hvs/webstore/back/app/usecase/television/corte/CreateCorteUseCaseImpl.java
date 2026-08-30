package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.app.command.television.corte.CreateCorteCommand;
import com.hvs.webstore.back.app.output.television.corte.CreateCorteOutput;
import com.hvs.webstore.back.domain.entity.television.corte.Corte;
import com.hvs.webstore.back.domain.entity.television.corte.CorteDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateCorteUseCaseImpl extends CreateCorteUseCase {

    private final CorteDomainGateway gateway;

    public CreateCorteUseCaseImpl(
            CorteDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateCorteOutput> execute(CreateCorteCommand aIn) {

        final var notification = Notification.create();
        final var corte = Corte.create(aIn.aArquivoId(),
                                       aIn.aTipoCode(),
                                       aIn.aDuracao(),
                                       aIn.aEpisodioId(),
                                       aIn.aInicio(),
                                       aIn.aFim());
        corte.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(corte);
    }

    @Transactional
    private Either<Notification, CreateCorteOutput> create(final Corte aCorte){

        return Try(() -> this.gateway.create(aCorte))
                .toEither()
                .bimap(Notification::create, CreateCorteOutput::from);
    }
}
