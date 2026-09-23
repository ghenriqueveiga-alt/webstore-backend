package com.hvs.ws.back.app.usecase.canal;

import com.hvs.ws.back.app.command.canal.UpdateCanalCommand;
import com.hvs.ws.back.app.output.canal.UpdateCanalOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateCanalUseCase extends UseCase<UpdateCanalCommand, Either<Notification, UpdateCanalOutput>> {
}
