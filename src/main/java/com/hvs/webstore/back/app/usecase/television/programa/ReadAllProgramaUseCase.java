package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.app.command.television.programa.ReadAllProgramaCommand;
import com.hvs.webstore.back.app.output.television.programa.ReadAllProgramaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllProgramaUseCase extends UseCase<ReadAllProgramaCommand, Either<Notification, ReadAllProgramaOutput>> {
}