package com.hvs.webstore.back.app.usecase.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.ReadArquivoCommand;
import com.hvs.webstore.back.app.output.television.arquivo.ReadArquivoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadArquivoUseCase extends UseCase<ReadArquivoCommand, Either<Notification, ReadArquivoOutput>> {
}