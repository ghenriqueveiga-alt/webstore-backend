package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.CreateMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.CreateMarcaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateMarcaUseCase extends UseCase<CreateMarcaCommand, Either<Notification, CreateMarcaOutput>> {}
