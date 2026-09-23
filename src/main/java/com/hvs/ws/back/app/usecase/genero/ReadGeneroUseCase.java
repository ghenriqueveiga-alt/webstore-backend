package com.hvs.ws.back.app.usecase.genero;

import com.hvs.ws.back.app.command.genero.ReadGeneroCommand;
import com.hvs.ws.back.app.output.genero.ReadGeneroOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadGeneroUseCase extends UseCase<ReadGeneroCommand, Either<Notification, ReadGeneroOutput>> {
}
