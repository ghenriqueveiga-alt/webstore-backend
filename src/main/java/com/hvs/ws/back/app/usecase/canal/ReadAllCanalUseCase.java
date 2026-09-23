package com.hvs.ws.back.app.usecase.canal;

import com.hvs.ws.back.app.command.canal.ReadAllCanalCommand;
import com.hvs.ws.back.app.output.canal.ReadAllCanalOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllCanalUseCase extends UseCase<ReadAllCanalCommand, Either<Notification, ReadAllCanalOutput>> {
}
