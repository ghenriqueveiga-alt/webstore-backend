package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.ReadPixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.ReadPixOutput;
import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixId;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadPixUseCaseImpl extends ReadPixUseCase {

    private final PixDomainGateway gateway;

    public ReadPixUseCaseImpl(PixDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadPixOutput> execute(ReadPixCommand aPixCommand) {

        Optional<Pix> aPixDB = aPixCommand.aId() != null ?
                gateway.read(PixId.from(aPixCommand.aId())) : gateway.readByUuid(PixUuid.from(aPixCommand.aUuid()));

        if (aPixDB.isPresent())
            return Try(aPixDB::get).toEither().bimap(Notification::create, ReadPixOutput::from);

        var aPixId = aPixCommand.aId() != null ? String.valueOf(aPixCommand.aId()) : aPixCommand.aUuid();

        return Either.left(Notification.create(new Error("Pix not found: " + aPixId)));
    }
}
