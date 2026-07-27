package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.app.command.television.programa.UpdateProgramaCommand;
import com.hvs.webstore.back.app.output.television.programa.UpdateProgramaOutput;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaDomainGateway;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaId;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateProgramaUseCaseImpl extends UpdateProgramaUseCase {

    private final ProgramaDomainGateway gateway;

    public UpdateProgramaUseCaseImpl(ProgramaDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateProgramaOutput> execute(UpdateProgramaCommand aProgramaCommand) {

        Optional<Programa> programaDb;

        if (aProgramaCommand.aId() != null) {
            programaDb = this.gateway.read(ProgramaId.from(aProgramaCommand.aId()));
        } else {
            programaDb = this.gateway.readByUuid(ProgramaUuid.from(aProgramaCommand.aUuid()));
        }

        if (programaDb.isPresent()) {
            final var notification = Notification.create();
            final var programa = Programa.update(programaDb.get().getId().getValue(),
                                                 programaDb.get().getUuid().getValue(),
                                                 aProgramaCommand.aStatusDesc(),
                                                 aProgramaCommand.aNome(),
                                                 aProgramaCommand.aEmProducao(),
                                                 aProgramaCommand.aTipoDesc(),
                                                 aProgramaCommand.aTemporadas(),
                                                 aProgramaCommand.aEpisodioIds(),
                                                 aProgramaCommand.aLancamento(),
                                                 aProgramaCommand.aEncerramento(),
                                                 aProgramaCommand.aBlocoIds());
            programa.validate(notification);
            return notification.hasError() ? Left(notification) : update(programa);
        } else {
            String responseId;

            if (aProgramaCommand.aId() != null) {
                responseId = String.valueOf(aProgramaCommand.aId());
            } else {
                responseId = aProgramaCommand.aUuid();
            }

            return Either.left(Notification.create(new Error("The Program with id: " + responseId + " could not be found.")));
        }
    }

    @Transactional
    private Either<Notification, UpdateProgramaOutput> update(final Programa aPrograma){

        return Try(() -> this.gateway.update(aPrograma))
                .toEither().bimap(Notification::create, UpdateProgramaOutput::from);
    }
}
