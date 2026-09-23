package com.hvs.ws.back.app.usecase.bloco;

import com.hvs.ws.back.app.command.bloco.CreateBlocoCommand;
import com.hvs.ws.back.app.output.bloco.CreateBlocoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateBlocoUseCase extends UseCase<CreateBlocoCommand, Either<Notification, CreateBlocoOutput>> {
}