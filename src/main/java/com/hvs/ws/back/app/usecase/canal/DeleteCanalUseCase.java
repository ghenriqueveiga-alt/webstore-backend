package com.hvs.ws.back.app.usecase.canal;

import com.hvs.ws.back.app.command.canal.DeleteCanalCommand;
import com.hvs.ws.back.app.output.canal.DeleteCanalOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteCanalUseCase extends UseCase<DeleteCanalCommand, Either<Notification, DeleteCanalOutput>> {
}
