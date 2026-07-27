package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.app.command.television.grade.DeleteGradeCommand;
import com.hvs.webstore.back.app.output.television.grade.DeleteGradeOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteGradeUseCase extends UseCase<DeleteGradeCommand, Either<Notification, DeleteGradeOutput>> {
}