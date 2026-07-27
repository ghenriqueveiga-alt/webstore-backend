package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.CreatePixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.CreatePixOutput;
import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreatePixUseCaseImpl extends CreatePixUseCase {

    private final PixDomainGateway gateway;

    public CreatePixUseCaseImpl(PixDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreatePixOutput> execute(CreatePixCommand aPixCommand) {

        var notification = Notification.create();
        var pix = Pix.create(aPixCommand.aChavePix(),
                             aPixCommand.aTipoChavePix());
        pix.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(pix);
    }

    @Transactional
    private Either<Notification, CreatePixOutput> create(Pix aPix) {

        return Try(() -> gateway.create(aPix))
                .toEither().bimap(Notification::create, CreatePixOutput::from);
    }
}
