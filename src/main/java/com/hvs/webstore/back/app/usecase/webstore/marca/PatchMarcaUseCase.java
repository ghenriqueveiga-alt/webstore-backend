package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.PatchMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.PatchMarcaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchMarcaUseCase extends UseCase<PatchMarcaCommand, Either<Notification, PatchMarcaOutput>> {}
