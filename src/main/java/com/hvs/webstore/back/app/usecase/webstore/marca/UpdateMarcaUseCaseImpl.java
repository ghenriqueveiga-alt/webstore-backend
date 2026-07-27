package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.UpdateMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.UpdateMarcaOutput;
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

public class UpdateMarcaUseCaseImpl extends UpdateMarcaUseCase {

    private final MarcaDomainGateway gateway;

    public UpdateMarcaUseCaseImpl(MarcaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateMarcaOutput> execute(UpdateMarcaCommand aMarcaCommand) {

        Optional<Marca> aMarcaDB = aMarcaCommand.aId() != null ?
                gateway.read(MarcaId.from(aMarcaCommand.aId())) : gateway.readByUuid(MarcaUuid.from(aMarcaCommand.aUuid()));

        if (aMarcaDB.isEmpty())
            return Left(Notification.create(new Error("Marca not found: " + (aMarcaCommand.aId() != null ?
                    aMarcaCommand.aId() : aMarcaCommand.aUuid()))));

        var notification = Notification.create();
        var marca = Marca.update(aMarcaDB.get().getId().getValue(),
                                 aMarcaDB.get().getUuid().getValue(),
                                 aMarcaCommand.aStatusCode(),
                                 aMarcaCommand.aNome(),
                                 aMarcaCommand.aDescricao(),
                                 aMarcaCommand.aProdutoIds());
        marca.validate(notification);

        return notification.hasError() ? Left(notification) : update(marca);
    }
    @Transactional
    private Either<Notification, UpdateMarcaOutput> update(Marca aMarca) {

        return Try(() -> gateway.update(aMarca))
                .toEither().bimap(Notification::create, UpdateMarcaOutput::from);
    }
}
