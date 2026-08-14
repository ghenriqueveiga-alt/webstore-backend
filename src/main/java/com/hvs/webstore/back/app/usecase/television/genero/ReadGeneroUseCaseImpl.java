package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.app.command.television.genero.ReadGeneroCommand;
import com.hvs.webstore.back.app.output.television.genero.ReadGeneroOutput;
import com.hvs.webstore.back.domain.entity.television.genero.Genero;
import com.hvs.webstore.back.domain.entity.television.genero.GeneroDomainGateway;
import com.hvs.webstore.back.domain.entity.television.genero.GeneroId;
import com.hvs.webstore.back.domain.entity.television.genero.GeneroUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
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
