package com.hvs.ws.back.app.usecase.grade;

import com.hvs.ws.back.app.command.grade.ReadGradeCommand;
import com.hvs.ws.back.app.output.grade.ReadGradeOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadGradeUseCase extends UseCase<ReadGradeCommand, Either<Notification, ReadGradeOutput>> {
}