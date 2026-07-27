package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.ReadAllPixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.ReadAllPixOutput;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllPixUseCaseImpl extends ReadAllPixUseCase {

    private final PixDomainGateway gateway;

    public ReadAllPixUseCaseImpl(PixDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllPixOutput> execute(ReadAllPixCommand aPixCommand) {

        return Try(() -> gateway.readAll(aPixCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllPixOutput::from);
    }
}
