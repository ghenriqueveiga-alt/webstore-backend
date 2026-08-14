package com.hvs.webstore.back.app.usecase.television.canal;

import com.hvs.webstore.back.app.command.television.canal.ReadAllCanalCommand;
import com.hvs.webstore.back.app.output.television.canal.ReadAllCanalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllCanalUseCase extends UseCase<ReadAllCanalCommand, Either<Notification, ReadAllCanalOutput>> {
}
