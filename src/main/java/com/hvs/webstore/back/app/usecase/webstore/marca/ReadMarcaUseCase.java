package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.ReadMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.ReadMarcaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadMarcaUseCase extends UseCase<ReadMarcaCommand, Either<Notification, ReadMarcaOutput>> {}
