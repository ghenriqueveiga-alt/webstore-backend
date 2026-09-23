package com.hvs.ws.back.app.usecase.programa;

import com.hvs.ws.back.app.command.programa.ReadAllProgramaCommand;
import com.hvs.ws.back.app.output.programa.ReadAllProgramaOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllProgramaUseCase extends UseCase<ReadAllProgramaCommand, Either<Notification, ReadAllProgramaOutput>> {
}