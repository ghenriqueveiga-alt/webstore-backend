package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.app.command.television.grade.PatchGradeCommand;
import com.hvs.webstore.back.app.output.television.grade.PatchGradeOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchGradeUseCase extends UseCase<PatchGradeCommand, Either<Notification, PatchGradeOutput>> {
}