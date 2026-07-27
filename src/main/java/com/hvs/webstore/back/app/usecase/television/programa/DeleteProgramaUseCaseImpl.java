package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.app.command.television.programa.DeleteProgramaCommand;
import com.hvs.webstore.back.app.output.television.programa.DeleteProgramaOutput;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaDomainGateway;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaId;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
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
