package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.app.command.webstore.caracteristica.UpdateCaracteristicaCommand;
import com.hvs.webstore.back.app.output.webstore.caracteristica.UpdateCaracteristicaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateCaracteristicaUseCase extends UseCase<UpdateCaracteristicaCommand, Either<Notification, UpdateCaracteristicaOutput>> {}
