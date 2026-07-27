package com.hvs.webstore.back.app.usecase.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.PatchArquivoCommand;
import com.hvs.webstore.back.app.output.television.arquivo.PatchArquivoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchArquivoUseCase extends UseCase<PatchArquivoCommand, Either<Notification, PatchArquivoOutput>> {
}