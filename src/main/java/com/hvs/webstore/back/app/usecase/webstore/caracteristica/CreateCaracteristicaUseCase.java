package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.app.command.webstore.caracteristica.CreateCaracteristicaCommand;
import com.hvs.webstore.back.app.output.webstore.caracteristica.CreateCaracteristicaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateCaracteristicaUseCase extends UseCase<CreateCaracteristicaCommand, Either<Notification, CreateCaracteristicaOutput>> {}
