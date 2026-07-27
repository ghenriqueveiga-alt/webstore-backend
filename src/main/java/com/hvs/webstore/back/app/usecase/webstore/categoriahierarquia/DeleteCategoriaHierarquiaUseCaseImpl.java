package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.DeleteCategoriaHierarquiaCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.DeleteCategoriaHierarquiaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaId;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteCategoriaHierarquiaUseCaseImpl extends DeleteCategoriaHierarquiaUseCase {

    private final CategoriaHierarquiaDomainGateway gateway;

    public DeleteCategoriaHierarquiaUseCaseImpl(CategoriaHierarquiaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteCategoriaHierarquiaOutput> execute(DeleteCategoriaHierarquiaCommand aCategoriaHierarquiaCommand) {

        Optional<CategoriaHierarquia> aCategoriaHierarquiaDB = aCategoriaHierarquiaCommand.aId() != null ?
                gateway.read(CategoriaHierarquiaId.from(aCategoriaHierarquiaCommand.aId())) : gateway.readByUuid(CategoriaHierarquiaUuid.from(aCategoriaHierarquiaCommand.aUuid()));

        if (aCategoriaHierarquiaDB.isEmpty())
            return Either.left(Notification.create(new Error("CategoriaHierarquia not found: " + (aCategoriaHierarquiaCommand.aId() != null ?
                    aCategoriaHierarquiaCommand.aId() : aCategoriaHierarquiaCommand.aUuid()))));

        return delete(aCategoriaHierarquiaDB.get());
    }

    @Transactional
    private Either<Notification, DeleteCategoriaHierarquiaOutput> delete(CategoriaHierarquia aCategoriaHierarquia) {

        return Try(() -> {
            gateway.delete(aCategoriaHierarquia);
            return DeleteCategoriaHierarquiaOutput.from(aCategoriaHierarquia);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
