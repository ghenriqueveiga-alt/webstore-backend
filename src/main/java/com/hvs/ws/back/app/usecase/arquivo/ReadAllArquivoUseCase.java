package com.hvs.ws.back.app.usecase.arquivo;

import com.hvs.ws.back.app.command.arquivo.ReadAllArquivoCommand;
import com.hvs.ws.back.app.output.arquivo.ReadAllArquivoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllArquivoUseCase extends UseCase<ReadAllArquivoCommand, Either<Notification, ReadAllArquivoOutput>> {
}