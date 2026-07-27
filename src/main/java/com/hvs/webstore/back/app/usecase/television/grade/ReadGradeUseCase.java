package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.app.command.television.grade.ReadGradeCommand;
import com.hvs.webstore.back.app.output.television.grade.ReadGradeOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadGradeUseCase extends UseCase<ReadGradeCommand, Either<Notification, ReadGradeOutput>> {
}