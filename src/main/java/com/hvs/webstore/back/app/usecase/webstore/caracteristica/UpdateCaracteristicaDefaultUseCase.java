package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.app.command.webstore.caracteristica.UpdateCaracteristicaCommand;
import com.hvs.webstore.back.app.output.webstore.caracteristica.UpdateCaracteristicaOutput;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaId;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateCaracteristicaDefaultUseCase extends UpdateCaracteristicaUseCase {

    private final CaracteristicaDomainGateway gateway;

    public UpdateCaracteristicaDefaultUseCase(CaracteristicaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateCaracteristicaOutput> execute(UpdateCaracteristicaCommand aCaracteristicaCommand) {

        Optional<Caracteristica> aCaracteristicaDB = aCaracteristicaCommand.aId() != null ?
                gateway.read(CaracteristicaId.from(aCaracteristicaCommand.aId())) : gateway.readByUuid(CaracteristicaUuid.from(aCaracteristicaCommand.aUuid()));

        if (aCaracteristicaDB.isEmpty())
            return Either.left(Notification.create(new Error("Caracteristica not found: " + (aCaracteristicaCommand.aId() != null ?
                    aCaracteristicaCommand.aId() : aCaracteristicaCommand.aUuid()))));

        var notification = Notification.create();
        var caracteristica = Caracteristica.update(aCaracteristicaDB.get().getId().getValue(),
                                                   aCaracteristicaDB.get().getUuid().getValue(),
                                                   aCaracteristicaCommand.aStatusCode(),
                                                   aCaracteristicaCommand.aNome(),
                                                   aCaracteristicaCommand.aDescricao());
        caracteristica.validate(notification);

        return notification.hasError() ? Left(notification) : update(caracteristica);
    }

    @Transactional
    private Either<Notification, UpdateCaracteristicaOutput> update(Caracteristica aCaracteristica) {

        return Try(() -> gateway.update(aCaracteristica)).toEither().bimap(Notification::create, UpdateCaracteristicaOutput::from);
    }
}
