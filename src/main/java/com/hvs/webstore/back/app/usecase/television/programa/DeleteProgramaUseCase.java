package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.app.command.television.programa.DeleteProgramaCommand;
import com.hvs.webstore.back.app.output.television.programa.DeleteProgramaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteProgramaUseCase extends UseCase<DeleteProgramaCommand, Either<Notification, DeleteProgramaOutput>> {
}