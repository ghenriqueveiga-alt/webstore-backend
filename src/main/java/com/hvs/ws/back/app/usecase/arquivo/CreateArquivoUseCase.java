package com.hvs.ws.back.app.usecase.arquivo;

import com.hvs.ws.back.app.command.arquivo.CreateArquivoCommand;
import com.hvs.ws.back.app.output.arquivo.CreateArquivoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateArquivoUseCase extends UseCase<CreateArquivoCommand, Either<Notification, CreateArquivoOutput>> {
}