package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.app.command.webstore.caracteristica.ReadAllCaracteristicaCommand;
import com.hvs.webstore.back.app.output.webstore.caracteristica.ReadAllCaracteristicaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllCaracteristicaUseCase extends UseCase<ReadAllCaracteristicaCommand, Either<Notification, ReadAllCaracteristicaOutput>> {}
