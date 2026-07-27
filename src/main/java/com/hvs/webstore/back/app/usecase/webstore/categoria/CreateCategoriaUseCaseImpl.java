package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.app.command.webstore.categoria.CreateCategoriaCommand;
import com.hvs.webstore.back.app.output.webstore.categoria.CreateCategoriaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateCategoriaUseCaseImpl extends CreateCategoriaUseCase {

    private final CategoriaDomainGateway gateway;

    public CreateCategoriaUseCaseImpl(CategoriaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateCategoriaOutput> execute(CreateCategoriaCommand aCategoriaCommand) {

        var notification = Notification.create();
        var categoria = Categoria.create(aCategoriaCommand.aNome(),
                                         aCategoriaCommand.aDescricao(),
                                         aCategoriaCommand.aProdutoIds());
        categoria.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(categoria);
    }

    @Transactional
    private Either<Notification, CreateCategoriaOutput> create(Categoria aCategoria) {

        return Try(() -> gateway.create(aCategoria))
                .toEither().bimap(Notification::create, CreateCategoriaOutput::from);
    }
}
