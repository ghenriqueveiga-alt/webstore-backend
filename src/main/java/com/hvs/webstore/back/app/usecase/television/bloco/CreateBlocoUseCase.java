package com.hvs.webstore.back.app.usecase.television.bloco;

import com.hvs.webstore.back.app.command.television.bloco.CreateBlocoCommand;
import com.hvs.webstore.back.app.output.television.bloco.CreateBlocoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateBlocoUseCase extends UseCase<CreateBlocoCommand, Either<Notification, CreateBlocoOutput>> {
}