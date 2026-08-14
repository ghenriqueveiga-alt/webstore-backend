package com.hvs.webstore.back.app.usecase.television.canal;

import com.hvs.webstore.back.app.command.television.canal.UpdateCanalCommand;
import com.hvs.webstore.back.app.output.television.canal.UpdateCanalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateCanalUseCase extends UseCase<UpdateCanalCommand, Either<Notification, UpdateCanalOutput>> {
}
