package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.app.command.television.corte.ReadAllCorteCommand;
import com.hvs.webstore.back.app.output.television.corte.ReadAllCorteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllCorteUseCase extends UseCase<ReadAllCorteCommand, Either<Notification, ReadAllCorteOutput>> {
}