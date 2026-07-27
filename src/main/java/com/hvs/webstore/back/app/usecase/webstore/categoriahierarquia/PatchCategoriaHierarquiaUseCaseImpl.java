package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.PatchCategoriaHierarquiaCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.PatchCategoriaHierarquiaOutput;
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

public class PatchCategoriaHierarquiaUseCaseImpl extends PatchCategoriaHierarquiaUseCase {

    private final CategoriaHierarquiaDomainGateway gateway;

    public PatchCategoriaHierarquiaUseCaseImpl(CategoriaHierarquiaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchCategoriaHierarquiaOutput> execute(PatchCategoriaHierarquiaCommand aCategoriaHierarquiaCommand) {

        Optional<CategoriaHierarquia> aCategoriaHierarquiaDB = aCategoriaHierarquiaCommand.aId() != null ?
                gateway.read(CategoriaHierarquiaId.from(aCategoriaHierarquiaCommand.aId())) : gateway.readByUuid(CategoriaHierarquiaUuid.from(aCategoriaHierarquiaCommand.aUuid()));

        if (aCategoriaHierarquiaDB.isEmpty())
            return Either.left(Notification.create(new Error("CategoriaHierarquia not found: " + (aCategoriaHierarquiaCommand.aId() != null ?
                    aCategoriaHierarquiaCommand.aId() : aCategoriaHierarquiaCommand.aUuid()))));

        var notification = Notification.create();
        var categoriaHierarquia = CategoriaHierarquia.patch(aCategoriaHierarquiaCommand.aStatusCode(),
                                                            aCategoriaHierarquiaCommand.aCategoriaId(),
                                                            aCategoriaHierarquiaCommand.aCategoriaPaiId(),
                                                            aCategoriaHierarquiaCommand.aNivel(),
                                                            aCategoriaHierarquiaDB.get());
        categoriaHierarquia.validate(notification);

        return notification.hasError() ? Left(notification) : patch(categoriaHierarquia);
    }

    @Transactional
    private Either<Notification, PatchCategoriaHierarquiaOutput> patch(CategoriaHierarquia aCategoriaHierarquia) {

        return Try(() -> gateway.patch(aCategoriaHierarquia))
                .toEither().bimap(Notification::create, PatchCategoriaHierarquiaOutput::from);
    }
}
