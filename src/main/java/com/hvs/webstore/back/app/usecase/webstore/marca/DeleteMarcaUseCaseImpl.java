package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.DeleteMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.DeleteMarcaOutput;
import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaId;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteMarcaUseCaseImpl extends DeleteMarcaUseCase {

    private final MarcaDomainGateway gateway;

    public DeleteMarcaUseCaseImpl(MarcaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteMarcaOutput> execute(DeleteMarcaCommand aMarcaCommand) {

        Optional<Marca> aMarcaDB = aMarcaCommand.aId() != null ?
                gateway.read(MarcaId.from(aMarcaCommand.aId())) : gateway.readByUuid(MarcaUuid.from(aMarcaCommand.aUuid()));

        if (aMarcaDB.isEmpty())
            return Either.left(Notification.create(new Error("Marca not found: " + (aMarcaCommand.aId() != null ?
                    aMarcaCommand.aId() : aMarcaCommand.aUuid()))));

        return delete(aMarcaDB.get());
    }

    @Transactional
    private Either<Notification, DeleteMarcaOutput> delete(Marca aMarca) {

        return Try(() -> {
            gateway.delete(aMarca);
            return DeleteMarcaOutput.from(aMarca);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
