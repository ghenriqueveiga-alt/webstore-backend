package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.app.command.webstore.categoria.DeleteCategoriaCommand;
import com.hvs.webstore.back.app.output.webstore.categoria.DeleteCategoriaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaId;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteCategoriaUseCaseImpl extends DeleteCategoriaUseCase {

    private final CategoriaDomainGateway gateway;

    public DeleteCategoriaUseCaseImpl(CategoriaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteCategoriaOutput> execute(DeleteCategoriaCommand aCategoriaCommand) {

        Optional<Categoria> aCategoriaDB = aCategoriaCommand.aId() != null ?
                gateway.read(CategoriaId.from(aCategoriaCommand.aId())) : gateway.readByUuid(CategoriaUuid.from(aCategoriaCommand.aUuid()));

        if (aCategoriaDB.isEmpty())
            return Either.left(Notification.create(new Error("Categoria not found: " + (aCategoriaCommand.aId() != null ?
                    aCategoriaCommand.aId() : aCategoriaCommand.aUuid()))));

        return delete(aCategoriaDB.get());
    }

    @Transactional
    private Either<Notification, DeleteCategoriaOutput> delete(Categoria aCategoria) {

        return Try(() -> {
            gateway.delete(aCategoria);
            return DeleteCategoriaOutput.from(aCategoria);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
