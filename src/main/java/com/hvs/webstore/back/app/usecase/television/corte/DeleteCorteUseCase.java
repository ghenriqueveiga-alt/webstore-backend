package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.app.command.television.corte.DeleteCorteCommand;
import com.hvs.webstore.back.app.output.television.corte.DeleteCorteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteCorteUseCase extends UseCase<DeleteCorteCommand, Either<Notification, DeleteCorteOutput>> {
}