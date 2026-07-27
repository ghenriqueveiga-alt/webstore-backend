package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.app.command.television.programa.PatchProgramaCommand;
import com.hvs.webstore.back.app.output.television.programa.PatchProgramaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchProgramaUseCase extends UseCase<PatchProgramaCommand, Either<Notification, PatchProgramaOutput>> {
}