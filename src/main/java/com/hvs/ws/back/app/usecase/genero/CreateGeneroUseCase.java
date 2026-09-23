package com.hvs.ws.back.app.usecase.genero;

import com.hvs.ws.back.app.command.genero.CreateGeneroCommand;
import com.hvs.ws.back.app.output.genero.CreateGeneroOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateGeneroUseCase extends UseCase<CreateGeneroCommand, Either<Notification, CreateGeneroOutput>> {
}
