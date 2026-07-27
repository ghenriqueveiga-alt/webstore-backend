package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.DeletePixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.DeletePixOutput;
import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixId;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeletePixUseCaseImpl extends DeletePixUseCase {

    private final PixDomainGateway gateway;

    public DeletePixUseCaseImpl(PixDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeletePixOutput> execute(DeletePixCommand aPixCommand) {

        Optional<Pix> aPixDB = aPixCommand.aId() != null ?
                gateway.read(PixId.from(aPixCommand.aId())) : gateway.readByUuid(PixUuid.from(aPixCommand.aUuid()));

        if (aPixDB.isEmpty())
            return Either.left(Notification.create(new Error("Pix not found: " + (aPixCommand.aId() != null ?
                    aPixCommand.aId() : aPixCommand.aUuid()))));

        return delete(aPixDB.get());
    }

    @Transactional
    private Either<Notification, DeletePixOutput> delete(Pix aPix) {

        return Try(() -> {
            gateway.delete(aPix);
            return DeletePixOutput.from(aPix);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
