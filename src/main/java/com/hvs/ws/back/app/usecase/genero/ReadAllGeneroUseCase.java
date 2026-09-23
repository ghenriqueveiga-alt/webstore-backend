package com.hvs.ws.back.app.usecase.genero;

import com.hvs.ws.back.app.command.genero.ReadAllGeneroCommand;
import com.hvs.ws.back.app.output.genero.ReadAllGeneroOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllGeneroUseCase extends UseCase<ReadAllGeneroCommand, Either<Notification, ReadAllGeneroOutput>> {
}
