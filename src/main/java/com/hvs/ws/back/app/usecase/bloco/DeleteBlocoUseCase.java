package com.hvs.ws.back.app.usecase.bloco;

import com.hvs.ws.back.app.command.bloco.DeleteBlocoCommand;
import com.hvs.ws.back.app.output.bloco.DeleteBlocoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteBlocoUseCase extends UseCase<DeleteBlocoCommand, Either<Notification, DeleteBlocoOutput>> {
}