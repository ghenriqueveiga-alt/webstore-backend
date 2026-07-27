package com.hvs.webstore.back.app.usecase.television.bloco;

import com.hvs.webstore.back.app.command.television.bloco.DeleteBlocoCommand;
import com.hvs.webstore.back.app.output.television.bloco.DeleteBlocoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteBlocoUseCase extends UseCase<DeleteBlocoCommand, Either<Notification, DeleteBlocoOutput>> {
}