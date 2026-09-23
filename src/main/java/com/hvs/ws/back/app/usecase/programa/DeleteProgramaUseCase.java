package com.hvs.ws.back.app.usecase.programa;

import com.hvs.ws.back.app.command.programa.DeleteProgramaCommand;
import com.hvs.ws.back.app.output.programa.DeleteProgramaOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteProgramaUseCase extends UseCase<DeleteProgramaCommand, Either<Notification, DeleteProgramaOutput>> {
}