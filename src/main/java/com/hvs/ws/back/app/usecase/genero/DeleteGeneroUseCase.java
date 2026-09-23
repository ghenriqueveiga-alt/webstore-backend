package com.hvs.ws.back.app.usecase.genero;

import com.hvs.ws.back.app.command.genero.DeleteGeneroCommand;
import com.hvs.ws.back.app.output.genero.DeleteGeneroOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteGeneroUseCase extends UseCase<DeleteGeneroCommand, Either<Notification, DeleteGeneroOutput>> {
}
