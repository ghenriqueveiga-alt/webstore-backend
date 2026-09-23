package com.hvs.ws.back.app.usecase.genero;

import com.hvs.ws.back.app.command.genero.UpdateGeneroCommand;
import com.hvs.ws.back.app.output.genero.UpdateGeneroOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateGeneroUseCase extends UseCase<UpdateGeneroCommand, Either<Notification, UpdateGeneroOutput>> {
}
