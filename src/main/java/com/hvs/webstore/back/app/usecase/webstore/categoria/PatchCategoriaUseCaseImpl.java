package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.app.command.webstore.categoria.PatchCategoriaCommand;
import com.hvs.webstore.back.app.output.webstore.categoria.PatchCategoriaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaId;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchCategoriaUseCaseImpl extends PatchCategoriaUseCase {

    private final CategoriaDomainGateway gateway;

    public PatchCategoriaUseCaseImpl(CategoriaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchCategoriaOutput> execute(PatchCategoriaCommand aCategoriaCommand) {

        Optional<Categoria> aCategoriaDB = aCategoriaCommand.aId() != null ?
                gateway.read(CategoriaId.from(aCategoriaCommand.aId())) : gateway.readByUuid(CategoriaUuid.from(aCategoriaCommand.aUuid()));

        if (aCategoriaDB.isEmpty())
            return Either.left(Notification.create(new Error("Categoria not found: " + (aCategoriaCommand.aId() != null ?
                    aCategoriaCommand.aId() : aCategoriaCommand.aUuid()))));

        var notification = Notification.create();
        var categoria = Categoria.patch(aCategoriaCommand.aStatusCode(),
                                        aCategoriaCommand.aNome(),
                                        aCategoriaCommand.aDescricao(),
                                        aCategoriaCommand.aProdutoIds(),
                                        aCategoriaDB.get());
        categoria.validate(notification);

        return notification.hasError() ? Left(notification) : patch(categoria);
    }

    @Transactional
    private Either<Notification, PatchCategoriaOutput> patch(Categoria aCategoria) {

        return Try(() -> gateway.patch(aCategoria))
                .toEither().bimap(Notification::create, PatchCategoriaOutput::from);
    }
}
