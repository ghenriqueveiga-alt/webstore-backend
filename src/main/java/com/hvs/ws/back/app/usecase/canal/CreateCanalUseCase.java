package com.hvs.ws.back.app.usecase.canal;

import com.hvs.ws.back.app.command.canal.CreateCanalCommand;
import com.hvs.ws.back.app.output.canal.CreateCanalOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateCanalUseCase extends UseCase<CreateCanalCommand, Either<Notification, CreateCanalOutput>> {
}
