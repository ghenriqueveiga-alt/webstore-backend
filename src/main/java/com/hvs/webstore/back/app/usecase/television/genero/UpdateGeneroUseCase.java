package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.app.command.television.genero.UpdateGeneroCommand;
import com.hvs.webstore.back.app.output.television.genero.UpdateGeneroOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateGeneroUseCase extends UseCase<UpdateGeneroCommand, Either<Notification, UpdateGeneroOutput>> {
}
