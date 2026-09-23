package com.hvs.ws.back.app.usecase.grade;

import com.hvs.ws.back.app.command.grade.DeleteGradeCommand;
import com.hvs.ws.back.app.output.grade.DeleteGradeOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteGradeUseCase extends UseCase<DeleteGradeCommand, Either<Notification, DeleteGradeOutput>> {
}