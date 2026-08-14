package com.hvs.webstore.back.app.usecase.television.bloco;

import com.hvs.webstore.back.app.command.television.bloco.PatchBlocoCommand;
import com.hvs.webstore.back.app.output.television.bloco.PatchBlocoOutput;
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

public class PatchBlocoUseCaseImpl extends PatchBlocoUseCase {

    private final BlocoDomainGateway gateway;

    public PatchBlocoUseCaseImpl(
            BlocoDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchBlocoOutput> execute(PatchBlocoCommand aIn) {

        Optional<Bloco> blocoDb;

        if (aIn.aId() != null) {

            blocoDb = this.gateway.read(BlocoId.from(aIn.aId()));
        } else {

            blocoDb = this.gateway.readByUuid(BlocoUuid.from(aIn.aUuid()));
        }

        if (blocoDb.isPresent()) {

            final var notification = Notification.create();
            final var bloco = Bloco.patch(aIn.aStatusDesc(),
                                          aIn.aProgramaId(),
                                          aIn.aHorario(),
                                          aIn.aGradeId(),
                                          aIn.aDiaSemanaCode(),
                                          aIn.aFaixaHorarioCode(),
                                          aIn.aTipoBlocoCode(),
                                          blocoDb.get());
            bloco.validate(notification);

            return notification.hasError() ? Left(notification) : patch(bloco);
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
    private Either<Notification, PatchBlocoOutput> patch(final Bloco aBloco){

        return Try(() -> this.gateway.patch(aBloco))
                .toEither()
                .bimap(Notification::create, PatchBlocoOutput::from);
    }
}
