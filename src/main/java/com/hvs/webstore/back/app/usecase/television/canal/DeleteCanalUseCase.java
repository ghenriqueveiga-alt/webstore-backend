package com.hvs.webstore.back.app.usecase.television.canal;

import com.hvs.webstore.back.app.command.television.canal.DeleteCanalCommand;
import com.hvs.webstore.back.app.output.television.canal.DeleteCanalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteCanalUseCase extends UseCase<DeleteCanalCommand, Either<Notification, DeleteCanalOutput>> {
}
