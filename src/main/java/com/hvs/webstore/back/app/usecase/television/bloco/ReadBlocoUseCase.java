package com.hvs.webstore.back.app.usecase.television.bloco;

import com.hvs.webstore.back.app.command.television.bloco.ReadBlocoCommand;
import com.hvs.webstore.back.app.output.television.bloco.ReadBlocoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadBlocoUseCase extends UseCase<ReadBlocoCommand, Either<Notification, ReadBlocoOutput>>{
}