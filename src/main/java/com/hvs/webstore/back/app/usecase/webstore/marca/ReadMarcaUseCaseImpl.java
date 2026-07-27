package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.ReadMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.ReadMarcaOutput;
import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaId;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadMarcaUseCaseImpl extends ReadMarcaUseCase {

    private final MarcaDomainGateway gateway;

    public ReadMarcaUseCaseImpl(MarcaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadMarcaOutput> execute(ReadMarcaCommand aMarcaCommand) {

        Optional<Marca> aMarcaDB = aMarcaCommand.aId() != null ?
                gateway.read(MarcaId.from(aMarcaCommand.aId())) : gateway.readByUuid(MarcaUuid.from(aMarcaCommand.aUuid()));

        if (aMarcaDB.isPresent())
            return Try(aMarcaDB::get)
                    .toEither().bimap(Notification::create, ReadMarcaOutput::from);

        var aMarcaId = aMarcaCommand.aId() != null ? String.valueOf(aMarcaCommand.aId()) : aMarcaCommand.aUuid();

        return Either.left(Notification.create(new Error("Marca not found: " + aMarcaId)));
    }
}
