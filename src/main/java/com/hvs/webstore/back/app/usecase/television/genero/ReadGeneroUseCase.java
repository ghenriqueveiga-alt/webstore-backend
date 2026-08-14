package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.app.command.television.genero.ReadGeneroCommand;
import com.hvs.webstore.back.app.output.television.genero.ReadGeneroOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadGeneroUseCase extends UseCase<ReadGeneroCommand, Either<Notification, ReadGeneroOutput>> {
}
