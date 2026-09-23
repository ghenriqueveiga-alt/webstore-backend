package com.hvs.ws.back.app.usecase.grade;

import com.hvs.ws.back.app.command.grade.CreateGradeCommand;
import com.hvs.ws.back.app.output.grade.CreateGradeOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateGradeUseCase extends UseCase<CreateGradeCommand, Either<Notification, CreateGradeOutput>> {
}