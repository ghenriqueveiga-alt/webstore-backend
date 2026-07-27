package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.app.command.television.corte.CreateCorteCommand;
import com.hvs.webstore.back.app.output.television.corte.CreateCorteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateCorteUseCase extends UseCase<CreateCorteCommand, Either<Notification, CreateCorteOutput>> {
}