package com.hvs.ws.back.app.usecase.bloco;

import com.hvs.ws.back.app.command.bloco.UpdateBlocoCommand;
import com.hvs.ws.back.app.output.bloco.UpdateBlocoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateBlocoUseCase extends UseCase<UpdateBlocoCommand, Either<Notification, UpdateBlocoOutput>> {
}