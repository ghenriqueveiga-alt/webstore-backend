package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.app.command.webstore.categoria.ReadCategoriaCommand;
import com.hvs.webstore.back.app.output.webstore.categoria.ReadCategoriaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaId;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadCategoriaUseCaseImpl extends ReadCategoriaUseCase {

    private final CategoriaDomainGateway gateway;

    public ReadCategoriaUseCaseImpl(CategoriaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadCategoriaOutput> execute(ReadCategoriaCommand aCategoriaCommand) {

        Optional<Categoria> aCategoriaDB = aCategoriaCommand.aId() != null ?
                gateway.read(CategoriaId.from(aCategoriaCommand.aId())) : gateway.readByUuid(CategoriaUuid.from(aCategoriaCommand.aUuid()));

        if (aCategoriaDB.isPresent())
            return Try(aCategoriaDB::get).toEither().bimap(Notification::create, ReadCategoriaOutput::from);

        var aCategoriaId = aCategoriaCommand.aId() != null ? String.valueOf(aCategoriaCommand.aId()) : aCategoriaCommand.aUuid();

        return Either.left(Notification.create(new Error("Categoria not found: " + aCategoriaId)));
    }
}
