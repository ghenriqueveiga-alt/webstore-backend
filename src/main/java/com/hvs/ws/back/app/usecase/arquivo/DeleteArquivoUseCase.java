package com.hvs.ws.back.app.usecase.arquivo;

import com.hvs.ws.back.app.command.arquivo.DeleteArquivoCommand;
import com.hvs.ws.back.app.output.arquivo.DeleteArquivoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteArquivoUseCase extends UseCase<DeleteArquivoCommand, Either<Notification, DeleteArquivoOutput>> {
}