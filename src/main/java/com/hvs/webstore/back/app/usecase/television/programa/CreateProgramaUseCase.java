package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.app.command.television.programa.CreateProgramaCommand;
import com.hvs.webstore.back.app.output.television.programa.CreateProgramaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateProgramaUseCase extends UseCase<CreateProgramaCommand, Either<Notification, CreateProgramaOutput>> {
}