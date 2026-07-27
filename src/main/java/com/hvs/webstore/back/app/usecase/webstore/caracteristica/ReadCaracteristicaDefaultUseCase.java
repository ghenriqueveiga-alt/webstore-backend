package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.app.command.webstore.caracteristica.ReadCaracteristicaCommand;
import com.hvs.webstore.back.app.output.webstore.caracteristica.ReadCaracteristicaOutput;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaId;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadCaracteristicaDefaultUseCase extends ReadCaracteristicaUseCase {

    private final CaracteristicaDomainGateway gateway;

    public ReadCaracteristicaDefaultUseCase(CaracteristicaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadCaracteristicaOutput> execute(ReadCaracteristicaCommand aCaracteristicaCommand) {

        Optional<Caracteristica> aCaracteristicaDB = aCaracteristicaCommand.aId() != null ?
                gateway.read(CaracteristicaId.from(aCaracteristicaCommand.aId())) : gateway.readByUuid(CaracteristicaUuid.from(aCaracteristicaCommand.aUuid()));

        if (aCaracteristicaDB.isPresent())
            return Try(aCaracteristicaDB::get).toEither().bimap(Notification::create, ReadCaracteristicaOutput::from);

        var aCaracteristicaId = aCaracteristicaCommand.aId() != null ? String.valueOf(aCaracteristicaCommand.aId()) : aCaracteristicaCommand.aUuid();

        return Either.left(Notification.create(new Error("Caracteristica not found: " + aCaracteristicaId)));
    }
}
