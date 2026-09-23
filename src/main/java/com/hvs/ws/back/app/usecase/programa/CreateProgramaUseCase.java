package com.hvs.ws.back.app.usecase.programa;

import com.hvs.ws.back.app.command.programa.CreateProgramaCommand;
import com.hvs.ws.back.app.output.programa.CreateProgramaOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateProgramaUseCase extends UseCase<CreateProgramaCommand, Either<Notification, CreateProgramaOutput>> {
}