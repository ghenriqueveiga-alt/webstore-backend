package com.hvs.ws.back.app.usecase.canal;

import com.hvs.ws.back.app.command.canal.ReadCanalCommand;
import com.hvs.ws.back.app.output.canal.ReadCanalOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadCanalUseCase extends UseCase<ReadCanalCommand, Either<Notification, ReadCanalOutput>> {
}
