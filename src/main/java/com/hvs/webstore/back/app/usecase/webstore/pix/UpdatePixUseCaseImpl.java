package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.UpdatePixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.UpdatePixOutput;
import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixId;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdatePixUseCaseImpl extends UpdatePixUseCase {

    private final PixDomainGateway gateway;

    public UpdatePixUseCaseImpl(PixDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdatePixOutput> execute(UpdatePixCommand aPixCommand) {

        Optional<Pix> aPixDB = aPixCommand.aId() != null ?
                gateway.read(PixId.from(aPixCommand.aId())) : gateway.readByUuid(PixUuid.from(aPixCommand.aUuid()));

        if (aPixDB.isEmpty())
            return Left(Notification.create(new Error("Pix not found: " + (aPixCommand.aId() != null ?
                    aPixCommand.aId() : aPixCommand.aUuid()))));

        var notification = Notification.create();
        var pix = Pix.update(aPixDB.get().getId().getValue(),
                             aPixDB.get().getUuid().getValue(),
                             aPixCommand.aStatusCode(),
                             aPixCommand.aChavePix(),
                             aPixCommand.aTipoChavePix());
        pix.validate(notification);

        return notification.hasError() ? Left(notification) : update(pix);
    }

    @Transactional
    private Either<Notification, UpdatePixOutput> update(Pix aPix) {

        return Try(() -> gateway.update(aPix))
                .toEither().bimap(Notification::create, UpdatePixOutput::from);
    }
}
