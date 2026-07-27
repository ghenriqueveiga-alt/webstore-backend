package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.app.command.television.grade.CreateGradeCommand;
import com.hvs.webstore.back.app.output.television.grade.CreateGradeOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateGradeUseCase extends UseCase<CreateGradeCommand, Either<Notification, CreateGradeOutput>> {
}