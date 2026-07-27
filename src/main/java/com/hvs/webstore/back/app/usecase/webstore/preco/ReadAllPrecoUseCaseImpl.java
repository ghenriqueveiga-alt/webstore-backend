package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.ReadAllPrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.ReadAllPrecoOutput;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllPrecoUseCaseImpl extends ReadAllPrecoUseCase {

    private final PrecoDomainGateway gateway;

    public ReadAllPrecoUseCaseImpl(PrecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllPrecoOutput> execute(ReadAllPrecoCommand aPrecoCommand) {

        return Try(() -> gateway.readAll(aPrecoCommand.aSearchQuery()))
                .toEither().bimap(Notification::create, ReadAllPrecoOutput::from);
    }
}
