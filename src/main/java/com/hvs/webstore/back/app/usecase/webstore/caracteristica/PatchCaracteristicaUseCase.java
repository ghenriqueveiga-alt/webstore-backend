package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.app.command.webstore.caracteristica.PatchCaracteristicaCommand;
import com.hvs.webstore.back.app.output.webstore.caracteristica.PatchCaracteristicaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchCaracteristicaUseCase extends UseCase<PatchCaracteristicaCommand, Either<Notification, PatchCaracteristicaOutput>> {}
