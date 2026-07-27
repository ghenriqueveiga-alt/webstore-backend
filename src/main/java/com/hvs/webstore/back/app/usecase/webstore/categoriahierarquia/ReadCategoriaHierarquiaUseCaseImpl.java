package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.ReadCategoriaHierarquiaCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.ReadCategoriaHierarquiaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaId;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadCategoriaHierarquiaUseCaseImpl extends ReadCategoriaHierarquiaUseCase {

    private final CategoriaHierarquiaDomainGateway gateway;

    public ReadCategoriaHierarquiaUseCaseImpl(CategoriaHierarquiaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadCategoriaHierarquiaOutput> execute(ReadCategoriaHierarquiaCommand aCategoriaHierarquiaCommand) {

        Optional<CategoriaHierarquia> aCategoriaHierarquiaDB = aCategoriaHierarquiaCommand.aId() != null ?
                gateway.read(CategoriaHierarquiaId.from(aCategoriaHierarquiaCommand.aId())) : gateway.readByUuid(CategoriaHierarquiaUuid.from(aCategoriaHierarquiaCommand.aUuid()));

        if (aCategoriaHierarquiaDB.isPresent())
            return Try(aCategoriaHierarquiaDB::get).toEither().bimap(Notification::create, ReadCategoriaHierarquiaOutput::from);

        var aCategoriaHierarquiaId = aCategoriaHierarquiaCommand.aId() != null ? String.valueOf(aCategoriaHierarquiaCommand.aId()) : aCategoriaHierarquiaCommand.aUuid();

        return Either.left(Notification.create(new Error("CategoriaHierarquia not found: " + aCategoriaHierarquiaId)));
    }
}
