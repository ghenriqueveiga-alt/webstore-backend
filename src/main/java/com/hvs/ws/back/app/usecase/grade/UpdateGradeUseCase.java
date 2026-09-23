package com.hvs.ws.back.app.usecase.grade;

import com.hvs.ws.back.app.command.grade.UpdateGradeCommand;
import com.hvs.ws.back.app.output.grade.UpdateGradeOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateGradeUseCase extends UseCase<UpdateGradeCommand, Either<Notification, UpdateGradeOutput>> {
}