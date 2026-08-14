package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.app.command.television.genero.CreateGeneroCommand;
import com.hvs.webstore.back.app.output.television.genero.CreateGeneroOutput;
import com.hvs.webstore.back.domain.entity.television.genero.Genero;
import com.hvs.webstore.back.domain.entity.television.genero.GeneroDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateGeneroUseCaseImpl extends CreateGeneroUseCase {

    private final GeneroDomainGateway gateway;

    public CreateGeneroUseCaseImpl(GeneroDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateGeneroOutput> execute(CreateGeneroCommand aGeneroCommand) {

        final var notification = Notification.create();
        final var genero = Genero.create(aGeneroCommand.aNome(),
                                         aGeneroCommand.aDescricao());
        genero.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(genero);
    }

    @Transactional
    private Either<Notification, CreateGeneroOutput> create(final Genero aGenero) {

        return Try(() -> this.gateway.create(aGenero))
                .toEither().bimap(Notification::create, CreateGeneroOutput::from);
    }
}
