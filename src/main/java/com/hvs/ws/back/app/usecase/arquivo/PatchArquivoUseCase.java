package com.hvs.ws.back.app.usecase.arquivo;

import com.hvs.ws.back.app.command.arquivo.PatchArquivoCommand;
import com.hvs.ws.back.app.output.arquivo.PatchArquivoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchArquivoUseCase extends UseCase<PatchArquivoCommand, Either<Notification, PatchArquivoOutput>> {
}