package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.app.command.television.corte.UpdateCorteCommand;
import com.hvs.webstore.back.app.output.television.corte.UpdateCorteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateCorteUseCase extends UseCase<UpdateCorteCommand, Either<Notification, UpdateCorteOutput>> {
}