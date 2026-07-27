package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.ReadByParentIdCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.ReadAllCategoriaHierarquiaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadByParentIdUseCaseImpl extends ReadByParentIdUseCase {

    private final CategoriaHierarquiaDomainGateway gateway;

    public ReadByParentIdUseCaseImpl(CategoriaHierarquiaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCategoriaHierarquiaOutput> execute(ReadByParentIdCommand aCategoriaHierarquiaCommand) {

        List<CategoriaHierarquia> list = gateway.readByCategoriaPaiId(aCategoriaHierarquiaCommand.aParentId());

        if (list.isEmpty())
            return Either.left(Notification.create(new Error("No CategoriaHierarquia found for parentId: " + aCategoriaHierarquiaCommand.aParentId())));

        return Try(() -> list).toEither()
                .bimap(Notification::create, ReadAllCategoriaHierarquiaOutput::from);
    }
}
