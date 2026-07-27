package com.hvs.webstore.back.app.usecase.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.CreateArquivoCommand;
import com.hvs.webstore.back.app.output.television.arquivo.CreateArquivoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateArquivoUseCase extends UseCase<CreateArquivoCommand, Either<Notification, CreateArquivoOutput>> {
}