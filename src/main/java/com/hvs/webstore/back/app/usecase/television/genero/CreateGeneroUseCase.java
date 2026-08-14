package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.app.command.television.genero.CreateGeneroCommand;
import com.hvs.webstore.back.app.output.television.genero.CreateGeneroOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateGeneroUseCase extends UseCase<CreateGeneroCommand, Either<Notification, CreateGeneroOutput>> {
}
