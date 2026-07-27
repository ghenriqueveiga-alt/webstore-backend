package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.app.command.webstore.caracteristica.PatchCaracteristicaCommand;
import com.hvs.webstore.back.app.output.webstore.caracteristica.PatchCaracteristicaOutput;
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

public class PatchCaracteristicaDefaultUseCase extends PatchCaracteristicaUseCase {

    private final CaracteristicaDomainGateway gateway;

    public PatchCaracteristicaDefaultUseCase(CaracteristicaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchCaracteristicaOutput> execute(PatchCaracteristicaCommand aCaracteristicaCommand) {

        Optional<Caracteristica> aCaracteristicaDB = aCaracteristicaCommand.aId() != null ?
                gateway.read(CaracteristicaId.from(aCaracteristicaCommand.aId())) : gateway.readByUuid(CaracteristicaUuid.from(aCaracteristicaCommand.aUuid()));

        if (aCaracteristicaDB.isEmpty())
            return Either.left(Notification.create(new Error("Caracteristica not found: " + (aCaracteristicaCommand.aId() != null ?
                    aCaracteristicaCommand.aId() : aCaracteristicaCommand.aUuid()))));

        var notification = Notification.create();
        var caracteristica = Caracteristica.patch(aCaracteristicaCommand.aStatusCode(),
                                                  aCaracteristicaCommand.aNome(),
                                                  aCaracteristicaCommand.aDescricao(),
                                                  aCaracteristicaDB.get());
        caracteristica.validate(notification);

        return notification.hasError() ? Left(notification) : patch(caracteristica);
    }

    @Transactional
    private Either<Notification, PatchCaracteristicaOutput> patch(Caracteristica aCaracteristica) {

        return Try(() -> gateway.patch(aCaracteristica)).toEither().bimap(Notification::create, PatchCaracteristicaOutput::from);
    }
}
