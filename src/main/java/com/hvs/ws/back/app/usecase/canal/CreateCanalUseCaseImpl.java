package com.hvs.ws.back.app.usecase.canal;

import com.hvs.ws.back.app.command.canal.CreateCanalCommand;
import com.hvs.ws.back.app.output.canal.CreateCanalOutput;
import com.hvs.ws.back.domain.entity.canal.Canal;
import com.hvs.ws.back.domain.entity.canal.CanalDomainGateway;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateCanalUseCaseImpl extends CreateCanalUseCase {

    private final CanalDomainGateway gateway;

    public CreateCanalUseCaseImpl(CanalDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateCanalOutput> execute(CreateCanalCommand aCanalCommand) {

        final var notification = Notification.create();
        final var canal = Canal.create(aCanalCommand.aNome(),
                                       aCanalCommand.aDescricao(),
                                       aCanalCommand.aLogotipoUrl(),
                                       aCanalCommand.aSite());
        canal.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(canal);
    }

    @Transactional
    private Either<Notification, CreateCanalOutput> create(final Canal aCanal){

        return Try(() -> this.gateway.create(aCanal))
                .toEither().bimap(Notification::create, CreateCanalOutput::from);
    }
}
