package com.hvs.ws.back.app.usecase.grade;

import com.hvs.ws.back.app.command.grade.PatchGradeCommand;
import com.hvs.ws.back.app.output.grade.PatchGradeOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchGradeUseCase extends UseCase<PatchGradeCommand, Either<Notification, PatchGradeOutput>> {
}