package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.app.command.webstore.caracteristica.ReadAllCaracteristicaCommand;
import com.hvs.webstore.back.app.output.webstore.caracteristica.ReadAllCaracteristicaOutput;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllCaracteristicaDefaultUseCase extends ReadAllCaracteristicaUseCase {

    private final CaracteristicaDomainGateway gateway;

    public ReadAllCaracteristicaDefaultUseCase(CaracteristicaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCaracteristicaOutput> execute(ReadAllCaracteristicaCommand aCaracteristicaCommand) {

        Pagination<Caracteristica> caracteristicaPagination = gateway.readAll(aCaracteristicaCommand.aSearchQuery());
        List<Caracteristica> lista = caracteristicaPagination.aContent()
                .stream().filter(corte -> corte.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty())
            return Try(() -> gateway.readAll(aCaracteristicaCommand.aSearchQuery())).toEither().bimap(Notification::create, ReadAllCaracteristicaOutput::from);

        return Either.left(Notification
                .create(new Error("No Caracteristica was found.")));
    }
}
