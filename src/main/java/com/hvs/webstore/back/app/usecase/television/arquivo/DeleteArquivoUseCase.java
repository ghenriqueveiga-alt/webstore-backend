package com.hvs.webstore.back.app.usecase.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.DeleteArquivoCommand;
import com.hvs.webstore.back.app.output.television.arquivo.DeleteArquivoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteArquivoUseCase extends UseCase<DeleteArquivoCommand, Either<Notification, DeleteArquivoOutput>> {
}