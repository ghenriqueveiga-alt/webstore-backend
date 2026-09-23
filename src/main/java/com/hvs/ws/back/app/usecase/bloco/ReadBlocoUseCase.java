package com.hvs.ws.back.app.usecase.bloco;

import com.hvs.ws.back.app.command.bloco.ReadBlocoCommand;
import com.hvs.ws.back.app.output.bloco.ReadBlocoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadBlocoUseCase extends UseCase<ReadBlocoCommand, Either<Notification, ReadBlocoOutput>>{
}