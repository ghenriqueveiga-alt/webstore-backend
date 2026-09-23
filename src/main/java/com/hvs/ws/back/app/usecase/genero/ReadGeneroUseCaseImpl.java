package com.hvs.ws.back.app.usecase.genero;

import com.hvs.ws.back.app.command.genero.ReadGeneroCommand;
import com.hvs.ws.back.app.output.genero.ReadGeneroOutput;
import com.hvs.ws.back.domain.entity.genero.Genero;
import com.hvs.ws.back.domain.entity.genero.GeneroDomainGateway;
import com.hvs.ws.back.domain.entity.genero.GeneroId;
import com.hvs.ws.back.domain.entity.genero.GeneroUuid;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadGeneroUseCaseImpl extends ReadGeneroUseCase {

    private final GeneroDomainGateway gateway;

    public ReadGeneroUseCaseImpl(GeneroDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadGeneroOutput> execute(ReadGeneroCommand aGeneroCommand) {

        Optional<Genero> generoDb;

        if (aGeneroCommand.aId() != null) {
            generoDb = this.gateway.read(GeneroId.from(aGeneroCommand.aId()));
        } else {
            generoDb = this.gateway.readByUuid(GeneroUuid.from(aGeneroCommand.aUuid()));
        }

        if (generoDb.isPresent()) {
            return Try(generoDb::get).toEither().bimap(Notification::create, ReadGeneroOutput::from);
        } else {
            String responseId;

            if (aGeneroCommand.aId() != null) {
                responseId = String.valueOf(aGeneroCommand.aId());
            } else {
                responseId = aGeneroCommand.aUuid();
            }

            return Either.left(Notification.create(new Error("The Genero with id: " + responseId + " could not be found.")));
        }
    }
}
