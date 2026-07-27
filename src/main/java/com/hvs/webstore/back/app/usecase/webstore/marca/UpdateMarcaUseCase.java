package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.UpdateMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.UpdateMarcaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateMarcaUseCase extends UseCase<UpdateMarcaCommand, Either<Notification, UpdateMarcaOutput>> {}
