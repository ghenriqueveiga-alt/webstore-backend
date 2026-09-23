package com.hvs.ws.back.app.usecase.arquivo;

import com.hvs.ws.back.app.command.arquivo.ReadArquivoCommand;
import com.hvs.ws.back.app.output.arquivo.ReadArquivoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadArquivoUseCase extends UseCase<ReadArquivoCommand, Either<Notification, ReadArquivoOutput>> {
}