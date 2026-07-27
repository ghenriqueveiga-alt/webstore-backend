package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.app.command.webstore.caracteristica.DeleteCaracteristicaCommand;
import com.hvs.webstore.back.app.output.webstore.caracteristica.DeleteCaracteristicaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteCaracteristicaUseCase extends UseCase<DeleteCaracteristicaCommand, Either<Notification, DeleteCaracteristicaOutput>> {}
