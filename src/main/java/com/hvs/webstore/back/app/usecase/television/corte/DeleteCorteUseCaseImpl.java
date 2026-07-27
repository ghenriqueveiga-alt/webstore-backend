package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.app.command.television.corte.DeleteCorteCommand;
import com.hvs.webstore.back.app.output.television.corte.DeleteCorteOutput;
import com.hvs.webstore.back.domain.entity.television.corte.Corte;
import com.hvs.webstore.back.domain.entity.television.corte.CorteDomainGateway;
import com.hvs.webstore.back.domain.entity.television.corte.CorteId;
import com.hvs.webstore.back.domain.entity.television.corte.CorteUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteCorteUseCaseImpl extends DeleteCorteUseCase {

    private final CorteDomainGateway gateway;

    public DeleteCorteUseCaseImpl(
            CorteDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteCorteOutput> execute(DeleteCorteCommand aIn) {

        Optional<Corte> cordeDb;

        if (aIn.aId() != null) {

            cordeDb = this.gateway.read(CorteId.from(aIn.aId()));
        } else {

            cordeDb = this.gateway.readByUuid(CorteUuid.from(aIn.aUuid()));
        }

        if (cordeDb.isPresent()) {

            this.gateway.delete(cordeDb.get());

            return Try(cordeDb::get)
                    .toEither()
                    .bimap(Notification::create, DeleteCorteOutput::from);
        } else {

            String responseId;

            if (aIn.aId() != null) {

                responseId = String.valueOf(aIn.aId());
            } else {

                responseId = aIn.aUuid();
            }

            return Either.left(Notification
                    .create(new Error("The Cut with id: " + responseId + " could not be found.")));
        }
    }
}
