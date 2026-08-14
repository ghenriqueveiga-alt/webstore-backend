package com.hvs.webstore.back.app.usecase.television.canal;

import com.hvs.webstore.back.app.command.television.canal.CreateCanalCommand;
import com.hvs.webstore.back.app.output.television.canal.CreateCanalOutput;
import com.hvs.webstore.back.domain.entity.television.canal.Canal;
import com.hvs.webstore.back.domain.entity.television.canal.CanalDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
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
