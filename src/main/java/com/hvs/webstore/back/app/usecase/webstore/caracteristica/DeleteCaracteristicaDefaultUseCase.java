package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.app.command.webstore.caracteristica.DeleteCaracteristicaCommand;
import com.hvs.webstore.back.app.output.webstore.caracteristica.DeleteCaracteristicaOutput;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaId;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteCaracteristicaDefaultUseCase extends DeleteCaracteristicaUseCase {

    private final CaracteristicaDomainGateway gateway;

    public DeleteCaracteristicaDefaultUseCase(CaracteristicaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteCaracteristicaOutput> execute(DeleteCaracteristicaCommand aCaracteristicaCommand) {

        Optional<Caracteristica> aCaracteristicaDB = aCaracteristicaCommand.aId() != null ?
                gateway.read(CaracteristicaId.from(aCaracteristicaCommand.aId())) : gateway.readByUuid(CaracteristicaUuid.from(aCaracteristicaCommand.aUuid()));

        if (aCaracteristicaDB.isEmpty())
            return Either.left(Notification.create(new Error("Caracteristica not found: " + (aCaracteristicaCommand.aId() != null ?
                    aCaracteristicaCommand.aId() : aCaracteristicaCommand.aUuid()))));

        return delete(aCaracteristicaDB.get());
    }

    @Transactional
    private Either<Notification, DeleteCaracteristicaOutput> delete(Caracteristica aCaracteristica) {

        return Try(() -> {
            gateway.delete(aCaracteristica);

            return DeleteCaracteristicaOutput.from(aCaracteristica);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
