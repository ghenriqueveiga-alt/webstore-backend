package com.hvs.webstore.back.app.usecase.television.canal;

import com.hvs.webstore.back.app.command.television.canal.ReadCanalCommand;
import com.hvs.webstore.back.app.output.television.canal.ReadCanalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadCanalUseCase extends UseCase<ReadCanalCommand, Either<Notification, ReadCanalOutput>> {
}
