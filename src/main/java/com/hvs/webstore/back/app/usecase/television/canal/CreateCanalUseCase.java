package com.hvs.webstore.back.app.usecase.television.canal;

import com.hvs.webstore.back.app.command.television.canal.CreateCanalCommand;
import com.hvs.webstore.back.app.output.television.canal.CreateCanalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateCanalUseCase extends UseCase<CreateCanalCommand, Either<Notification, CreateCanalOutput>> {
}
