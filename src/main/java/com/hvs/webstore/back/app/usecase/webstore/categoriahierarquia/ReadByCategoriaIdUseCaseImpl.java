package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.ReadByCategoriaIdCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.ReadAllCategoriaHierarquiaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadByCategoriaIdUseCaseImpl extends ReadByCategoriaIdUseCase {

    private final CategoriaHierarquiaDomainGateway gateway;

    public ReadByCategoriaIdUseCaseImpl(CategoriaHierarquiaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCategoriaHierarquiaOutput> execute(ReadByCategoriaIdCommand aCategoriaHierarquiaCommand) {

        List<CategoriaHierarquia> list = gateway.readByCategoriaId(aCategoriaHierarquiaCommand.aCategoriaId());

        if (list.isEmpty())
            return Either.left(Notification.create(new Error("No CategoriaHierarquia found for categoriaId: " + aCategoriaHierarquiaCommand.aCategoriaId())));

        return Try(() -> list).toEither()
                .bimap(Notification::create, ReadAllCategoriaHierarquiaOutput::from);
    }
}
