package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.PatchMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.PatchMarcaOutput;
import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaId;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchMarcaUseCaseImpl extends PatchMarcaUseCase {

    private final MarcaDomainGateway gateway;

    public PatchMarcaUseCaseImpl(MarcaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchMarcaOutput> execute(PatchMarcaCommand aMarcaCommand) {

        Optional<Marca> aMarcaDB = aMarcaCommand.aId() != null ?
                gateway.read(MarcaId.from(aMarcaCommand.aId())) : gateway.readByUuid(MarcaUuid.from(aMarcaCommand.aUuid()));

        if (aMarcaDB.isEmpty())
            return Left(Notification.create(new Error("Marca not found: " + (aMarcaCommand.aId() != null ?
                    aMarcaCommand.aId() : aMarcaCommand.aUuid()))));

        var notification = Notification.create();
        var marca = Marca.patch(aMarcaCommand.aStatusCode(),
                                aMarcaCommand.aNome(),
                                aMarcaCommand.aDescricao(),
                                aMarcaCommand.aProdutoIds(),
                                aMarcaDB.get());
        marca.validate(notification);

        return notification.hasError() ? Left(notification) : patch(marca);
    }
    @Transactional
    private Either<Notification, PatchMarcaOutput> patch(Marca aMarca) {

        return Try(() -> gateway.patch(aMarca)).
                toEither().bimap(Notification::create, PatchMarcaOutput::from);
    }
}
