package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.ReadAllMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.ReadAllMarcaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllMarcaUseCase extends UseCase<ReadAllMarcaCommand, Either<Notification, ReadAllMarcaOutput>> {}
