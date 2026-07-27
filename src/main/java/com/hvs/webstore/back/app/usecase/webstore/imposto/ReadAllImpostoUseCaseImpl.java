package com.hvs.webstore.back.app.usecase.webstore.imposto;

import com.hvs.webstore.back.app.command.webstore.imposto.ReadAllImpostoCommand;
import com.hvs.webstore.back.app.output.webstore.imposto.ReadAllImpostoOutput;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllImpostoUseCaseImpl extends ReadAllImpostoUseCase {

    private final ImpostoDomainGateway gateway;

    public ReadAllImpostoUseCaseImpl(ImpostoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllImpostoOutput> execute(ReadAllImpostoCommand aImpostoCommand) {

        return Try(() -> gateway.readAll(aImpostoCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllImpostoOutput::from);
    }
}
