package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.app.command.webstore.caracteristica.CreateCaracteristicaCommand;
import com.hvs.webstore.back.app.output.webstore.caracteristica.CreateCaracteristicaOutput;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateCaracteristicaDefaultUseCase extends CreateCaracteristicaUseCase {

    private final CaracteristicaDomainGateway gateway;

    public CreateCaracteristicaDefaultUseCase(CaracteristicaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateCaracteristicaOutput> execute(CreateCaracteristicaCommand aCaracteristicaCommand) {

        var notification = Notification.create();
        var caracteristica = Caracteristica.create(aCaracteristicaCommand.aNome(),
                                                   aCaracteristicaCommand.aDescricao());
        caracteristica.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(caracteristica);
    }

    @Transactional
    private Either<Notification, CreateCaracteristicaOutput> create(Caracteristica aCaracteristica) {

        return Try(() -> gateway.create(aCaracteristica)).toEither().bimap(Notification::create, CreateCaracteristicaOutput::from);
    }
}
