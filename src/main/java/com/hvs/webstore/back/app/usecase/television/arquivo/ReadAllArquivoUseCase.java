package com.hvs.webstore.back.app.usecase.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.ReadAllArquivoCommand;
import com.hvs.webstore.back.app.output.television.arquivo.ReadAllArquivoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllArquivoUseCase extends UseCase<ReadAllArquivoCommand, Either<Notification, ReadAllArquivoOutput>> {
}