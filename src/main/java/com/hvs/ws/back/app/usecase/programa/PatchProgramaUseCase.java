package com.hvs.ws.back.app.usecase.programa;

import com.hvs.ws.back.app.command.programa.PatchProgramaCommand;
import com.hvs.ws.back.app.output.programa.PatchProgramaOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchProgramaUseCase extends UseCase<PatchProgramaCommand, Either<Notification, PatchProgramaOutput>> {
}