package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.CreateMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.CreateMarcaOutput;
import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateMarcaUseCaseImpl extends CreateMarcaUseCase {

    private final MarcaDomainGateway gateway;

    public CreateMarcaUseCaseImpl(MarcaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateMarcaOutput> execute(CreateMarcaCommand aMarcaCommand) {

        var notification = Notification.create();
        var marca = Marca.create(aMarcaCommand.aNome(),
                                 aMarcaCommand.aDescricao(),
                                 aMarcaCommand.aProdutoIds());
        marca.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(marca);
    }

    @Transactional
    private Either<Notification, CreateMarcaOutput> create(Marca aMarca) {

        return Try(() -> gateway.create(aMarca))
                .toEither().bimap(Notification::create, CreateMarcaOutput::from);
    }
}
