package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.ReadAllPrecoPromocionalCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.ReadAllPrecoPromocionalOutput;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllPrecoPromocionalUseCaseImpl extends ReadAllPrecoPromocionalUseCase {

    private final PrecoPromocionalDomainGateway gateway;

    public ReadAllPrecoPromocionalUseCaseImpl(PrecoPromocionalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllPrecoPromocionalOutput> execute(ReadAllPrecoPromocionalCommand aPrecoPromocionalCommand) {

        return Try(() -> gateway.readAll(aPrecoPromocionalCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllPrecoPromocionalOutput::from);
    }
}
