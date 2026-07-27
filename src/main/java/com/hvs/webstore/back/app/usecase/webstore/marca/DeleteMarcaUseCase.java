package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.DeleteMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.DeleteMarcaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteMarcaUseCase extends UseCase<DeleteMarcaCommand, Either<Notification, DeleteMarcaOutput>> {}
