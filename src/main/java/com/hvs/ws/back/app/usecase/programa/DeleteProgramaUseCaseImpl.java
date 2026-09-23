package com.hvs.ws.back.app.usecase.programa;

import com.hvs.ws.back.app.command.programa.DeleteProgramaCommand;
import com.hvs.ws.back.app.output.programa.DeleteProgramaOutput;
import com.hvs.ws.back.domain.entity.programa.Programa;
import com.hvs.ws.back.domain.entity.programa.ProgramaDomainGateway;
import com.hvs.ws.back.domain.entity.programa.ProgramaId;
import com.hvs.ws.back.domain.entity.programa.ProgramaUuid;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteProgramaUseCaseImpl extends DeleteProgramaUseCase {

    private final ProgramaDomainGateway gateway;

    public DeleteProgramaUseCaseImpl(ProgramaDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteProgramaOutput> execute(DeleteProgramaCommand aProgramaCommand) {

        Optional<Programa> programaDb;

        if (aProgramaCommand.aId() != null) {
            programaDb = this.gateway.read(ProgramaId.from(aProgramaCommand.aId()));
        } else {
            programaDb = this.gateway.readByUuid(ProgramaUuid.from(aProgramaCommand.aUuid()));
        }

        if (programaDb.isPresent()) {
            this.gateway.delete(programaDb.get());
            return Try(programaDb::get)
                    .toEither().bimap(Notification::create, DeleteProgramaOutput::from);
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
}
