package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.app.command.television.corte.ReadCorteCommand;
import com.hvs.webstore.back.app.output.television.corte.ReadCorteOutput;
import com.hvs.webstore.back.domain.entity.television.corte.Corte;
import com.hvs.webstore.back.domain.entity.television.corte.CorteDomainGateway;
import com.hvs.webstore.back.domain.entity.television.corte.CorteId;
import com.hvs.webstore.back.domain.entity.television.corte.CorteUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadCorteUseCaseImpl extends ReadCorteUseCase {

    private final CorteDomainGateway gateway;

    public ReadCorteUseCaseImpl(
            CorteDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadCorteOutput> execute(ReadCorteCommand aIn) {

        Optional<Corte> corteDb;

        if (aIn.aId() != null) {

            corteDb = this.gateway.read(CorteId.from(aIn.aId()));
        } else {

            corteDb = this.gateway.readByUuid(CorteUuid.from(aIn.aUuid()));
        }

        if (corteDb.isPresent()) {

            return Try(corteDb::get).toEither().bimap(Notification::create, ReadCorteOutput::from);
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
