package com.hvs.ws.back.app.usecase.programa;

import com.hvs.ws.back.app.command.programa.ReadProgramaCommand;
import com.hvs.ws.back.app.output.programa.ReadProgramaOutput;
import com.hvs.ws.back.domain.entity.programa.Programa;
import com.hvs.ws.back.domain.entity.programa.ProgramaDomainGateway;
import com.hvs.ws.back.domain.entity.programa.ProgramaId;
import com.hvs.ws.back.domain.entity.programa.ProgramaUuid;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadProgramaUseCaseImpl extends ReadProgramaUseCase {

    private final ProgramaDomainGateway gateway;

    public ReadProgramaUseCaseImpl(ProgramaDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadProgramaOutput> execute(ReadProgramaCommand aProgramaCommand) {

        Optional<Programa> programaDb;

        if (aProgramaCommand.aId() != null) {
            programaDb = this.gateway.read(ProgramaId.from(aProgramaCommand.aId()));
        } else {
            programaDb = this.gateway.readByUuid(ProgramaUuid.from(aProgramaCommand.aUuid()));
        }

        if (programaDb.isPresent()) {
            return Try(programaDb::get).toEither().bimap(Notification::create, ReadProgramaOutput::from);
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
