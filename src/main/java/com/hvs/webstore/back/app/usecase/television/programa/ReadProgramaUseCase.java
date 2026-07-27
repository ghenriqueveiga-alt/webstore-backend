package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.app.command.television.programa.ReadProgramaCommand;
import com.hvs.webstore.back.app.output.television.programa.ReadProgramaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadProgramaUseCase extends UseCase<ReadProgramaCommand, Either<Notification, ReadProgramaOutput>> {
}