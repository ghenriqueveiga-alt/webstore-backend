package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.app.command.television.programa.UpdateProgramaCommand;
import com.hvs.webstore.back.app.output.television.programa.UpdateProgramaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateProgramaUseCase extends UseCase<UpdateProgramaCommand, Either<Notification, UpdateProgramaOutput>> {
}