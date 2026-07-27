package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.PatchPixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.PatchPixOutput;
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

public class PatchPixUseCaseImpl extends PatchPixUseCase {

    private final PixDomainGateway gateway;

    public PatchPixUseCaseImpl(PixDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchPixOutput> execute(PatchPixCommand aPixCommand) {

        Optional<Pix> aPixDB = aPixCommand.aId() != null ?
                gateway.read(PixId.from(aPixCommand.aId())) : gateway.readByUuid(PixUuid.from(aPixCommand.aUuid()));

        if (aPixDB.isEmpty())
            return Left(Notification.create(new Error("Pix not found: " + (aPixCommand.aId() != null ?
                    aPixCommand.aId() : aPixCommand.aUuid()))));

        var notification = Notification.create();
        var pix = Pix.patch(aPixCommand.aStatusCode(),
                            aPixCommand.aChavePix(),
                            aPixCommand.aTipoChavePix(),
                            aPixDB.get());
        pix.validate(notification);

        return notification.hasError() ? Left(notification) : patch(pix);
    }
    @Transactional
    private Either<Notification, PatchPixOutput> patch(Pix aPix) {

        return Try(() -> gateway.patch(aPix))
                .toEither().bimap(Notification::create, PatchPixOutput::from);
    }
}
