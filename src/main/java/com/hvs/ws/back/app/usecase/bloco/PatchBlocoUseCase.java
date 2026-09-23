package com.hvs.ws.back.app.usecase.bloco;

import com.hvs.ws.back.app.command.bloco.PatchBlocoCommand;
import com.hvs.ws.back.app.output.bloco.PatchBlocoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchBlocoUseCase extends UseCase<PatchBlocoCommand, Either<Notification, PatchBlocoOutput>> {
}