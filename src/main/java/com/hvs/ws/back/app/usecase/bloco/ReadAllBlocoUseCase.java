package com.hvs.ws.back.app.usecase.bloco;

import com.hvs.ws.back.app.command.bloco.ReadAllBlocoCommand;
import com.hvs.ws.back.app.output.bloco.ReadAllBlocoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllBlocoUseCase extends UseCase<ReadAllBlocoCommand, Either<Notification, ReadAllBlocoOutput>> {
}