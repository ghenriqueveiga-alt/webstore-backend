package com.hvs.webstore.back.app.usecase.television.bloco;

import com.hvs.webstore.back.app.command.television.bloco.UpdateBlocoCommand;
import com.hvs.webstore.back.app.output.television.bloco.UpdateBlocoOutput;
import com.hvs.webstore.back.domain.entity.television.bloco.Bloco;
import com.hvs.webstore.back.domain.entity.television.bloco.BlocoDomainGateway;
import com.hvs.webstore.back.domain.entity.television.bloco.BlocoId;
import com.hvs.webstore.back.domain.entity.television.bloco.BlocoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateBlocoUseCaseImpl extends UpdateBlocoUseCase {

    private final BlocoDomainGateway gateway;

    public UpdateBlocoUseCaseImpl(
            BlocoDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateBlocoOutput> execute(UpdateBlocoCommand aIn) {

        Optional<Bloco> blocoDb;

        if (aIn.aId() != null) {

            blocoDb = this.gateway.read(BlocoId.from(aIn.aId()));
        } else {

            blocoDb = this.gateway.readByUuid(BlocoUuid.from(aIn.aUuid()));
        }

        if (blocoDb.isPresent()) {

            final var notification = Notification.create();
            final var bloco = Bloco.update(blocoDb.get().getId().getValue(),
                                           blocoDb.get().getUuid().getValue(),
                                           aIn.aStatusDesc(),
                                           aIn.aProgramaId(),
                                           aIn.aHorario(),
                                           aIn.aGradeId());
            bloco.validate(notification);

            return notification.hasError() ? Left(notification) : update(bloco);
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

    @Transactional
    private Either<Notification, UpdateBlocoOutput> update(final Bloco aBloco){

        return Try(() -> this.gateway.update(aBloco))
                .toEither()
                .bimap(Notification::create, UpdateBlocoOutput::from);
    }
}
