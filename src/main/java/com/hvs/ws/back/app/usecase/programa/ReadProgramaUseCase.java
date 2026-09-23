package com.hvs.ws.back.app.usecase.programa;

import com.hvs.ws.back.app.command.programa.ReadProgramaCommand;
import com.hvs.ws.back.app.output.programa.ReadProgramaOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadProgramaUseCase extends UseCase<ReadProgramaCommand, Either<Notification, ReadProgramaOutput>> {
}