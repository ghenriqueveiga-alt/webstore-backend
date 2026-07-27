package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.app.command.television.corte.ReadCorteCommand;
import com.hvs.webstore.back.app.output.television.corte.ReadCorteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadCorteUseCase extends UseCase<ReadCorteCommand, Either<Notification, ReadCorteOutput>> {
}