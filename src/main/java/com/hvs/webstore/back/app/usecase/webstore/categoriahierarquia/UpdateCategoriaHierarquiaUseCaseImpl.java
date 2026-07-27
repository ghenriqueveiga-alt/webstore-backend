package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.UpdateCategoriaHierarquiaCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.UpdateCategoriaHierarquiaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaId;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateCategoriaHierarquiaUseCaseImpl extends UpdateCategoriaHierarquiaUseCase {

    private final CategoriaHierarquiaDomainGateway gateway;

    public UpdateCategoriaHierarquiaUseCaseImpl(CategoriaHierarquiaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateCategoriaHierarquiaOutput> execute(UpdateCategoriaHierarquiaCommand aCategoriaHierarquiaCommand) {

        Optional<CategoriaHierarquia> aCategoriaHierarquiaDB = aCategoriaHierarquiaCommand.aId() != null ?
                gateway.read(CategoriaHierarquiaId.from(aCategoriaHierarquiaCommand.aId())) : gateway.readByUuid(CategoriaHierarquiaUuid.from(aCategoriaHierarquiaCommand.aUuid()));

        if (aCategoriaHierarquiaDB.isEmpty())
            return Either.left(Notification.create(new Error("CategoriaHierarquia not found: " + (aCategoriaHierarquiaCommand.aId() != null ?
                    aCategoriaHierarquiaCommand.aId() : aCategoriaHierarquiaCommand.aUuid()))));

        var notification = Notification.create();
        var categoriaHierarquia = CategoriaHierarquia.update(aCategoriaHierarquiaDB.get().getId().getValue(),
                                                              aCategoriaHierarquiaDB.get().getUuid().getValue(),
                                                              aCategoriaHierarquiaCommand.aStatusCode(),
                                                              aCategoriaHierarquiaCommand.aCategoriaId(),
                                                              aCategoriaHierarquiaCommand.aCategoriaPaiId(),
                                                              aCategoriaHierarquiaCommand.aNivel());
        categoriaHierarquia.validate(notification);

        return notification.hasError() ? Left(notification) : update(categoriaHierarquia);
    }

    @Transactional
    private Either<Notification, UpdateCategoriaHierarquiaOutput> update(CategoriaHierarquia aCategoriaHierarquia) {

        return Try(() -> gateway.update(aCategoriaHierarquia))
                .toEither().bimap(Notification::create, UpdateCategoriaHierarquiaOutput::from);
    }
}
