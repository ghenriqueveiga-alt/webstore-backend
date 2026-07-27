package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.app.command.television.grade.ReadAllGradeCommand;
import com.hvs.webstore.back.app.output.television.grade.ReadAllGradeOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllGradeUseCase extends UseCase<ReadAllGradeCommand, Either<Notification, ReadAllGradeOutput>> {
}