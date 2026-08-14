package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.app.command.television.genero.DeleteGeneroCommand;
import com.hvs.webstore.back.app.output.television.genero.DeleteGeneroOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteGeneroUseCase extends UseCase<DeleteGeneroCommand, Either<Notification, DeleteGeneroOutput>> {
}
