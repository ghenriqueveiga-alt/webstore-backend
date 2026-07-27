package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.app.command.television.grade.UpdateGradeCommand;
import com.hvs.webstore.back.app.output.television.grade.UpdateGradeOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateGradeUseCase extends UseCase<UpdateGradeCommand, Either<Notification, UpdateGradeOutput>> {
}