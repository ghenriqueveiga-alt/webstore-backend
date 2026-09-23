package com.hvs.ws.back.app.usecase.grade;

import com.hvs.ws.back.app.command.grade.ReadAllGradeCommand;
import com.hvs.ws.back.app.output.grade.ReadAllGradeOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllGradeUseCase extends UseCase<ReadAllGradeCommand, Either<Notification, ReadAllGradeOutput>> {
}