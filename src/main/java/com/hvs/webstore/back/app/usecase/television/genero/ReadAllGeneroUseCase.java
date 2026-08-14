package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.app.command.television.genero.ReadAllGeneroCommand;
import com.hvs.webstore.back.app.output.television.genero.ReadAllGeneroOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllGeneroUseCase extends UseCase<ReadAllGeneroCommand, Either<Notification, ReadAllGeneroOutput>> {
}
