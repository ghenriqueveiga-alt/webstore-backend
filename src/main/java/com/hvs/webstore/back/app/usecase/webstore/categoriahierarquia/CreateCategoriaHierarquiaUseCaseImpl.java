package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.CreateCategoriaHierarquiaCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.CreateCategoriaHierarquiaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateCategoriaHierarquiaUseCaseImpl extends CreateCategoriaHierarquiaUseCase {

    private final CategoriaHierarquiaDomainGateway gateway;

    public CreateCategoriaHierarquiaUseCaseImpl(CategoriaHierarquiaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateCategoriaHierarquiaOutput> execute(CreateCategoriaHierarquiaCommand aCategoriaHierarquiaCommand) {

        var notification = Notification.create();
        var categoriaHierarquia = CategoriaHierarquia.create(aCategoriaHierarquiaCommand.aCategoriaId(),
                                                             aCategoriaHierarquiaCommand.aCategoriaPaiId(),
                                                             aCategoriaHierarquiaCommand.aNivel());
        categoriaHierarquia.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(categoriaHierarquia);
    }

    @Transactional
    private Either<Notification, CreateCategoriaHierarquiaOutput> create(CategoriaHierarquia aCategoriaHierarquia) {

        return Try(() -> gateway.create(aCategoriaHierarquia))
                .toEither().bimap(Notification::create, CreateCategoriaHierarquiaOutput::from);
    }
}
