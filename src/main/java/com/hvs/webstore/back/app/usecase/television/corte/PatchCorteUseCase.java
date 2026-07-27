package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.app.command.television.corte.PatchCorteCommand;
import com.hvs.webstore.back.app.output.television.corte.PatchCorteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchCorteUseCase extends UseCase<PatchCorteCommand, Either<Notification, PatchCorteOutput>> {
}