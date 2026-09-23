package com.hvs.ws.back.app.usecase.bloco;

import com.hvs.ws.back.app.command.bloco.DeleteBlocoCommand;
import com.hvs.ws.back.app.output.bloco.DeleteBlocoOutput;
import com.hvs.ws.back.domain.entity.bloco.Bloco;
import com.hvs.ws.back.domain.entity.bloco.BlocoDomainGateway;
import com.hvs.ws.back.domain.entity.bloco.BlocoId;
import com.hvs.ws.back.domain.entity.bloco.BlocoUuid;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteBlocoUseCaseImpl extends DeleteBlocoUseCase {

    private final BlocoDomainGateway gateway;

    public DeleteBlocoUseCaseImpl(
            BlocoDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteBlocoOutput> execute(DeleteBlocoCommand aIn) {

        Optional<Bloco> blocoDb;

        if (aIn.aId() != null) {

            blocoDb = this.gateway.read(BlocoId.from(aIn.aId()));
        } else {

            blocoDb = this.gateway.readByUuid(BlocoUuid.from(aIn.aUuid()));
        }

        if (blocoDb.isPresent()) {

            this.gateway.delete(blocoDb.get());

            return Try(blocoDb::get)
                    .toEither()
                    .bimap(Notification::create, DeleteBlocoOutput::from);
        } else {

            String responseId;

            if (aIn.aId() != null) {

                responseId = String.valueOf(aIn.aId());
            } else {

                responseId = aIn.aUuid();
            }

            return Either.left(Notification
                    .create(new Error("The Block with id: " + responseId + " could not be found.")));
        }
    }
}
