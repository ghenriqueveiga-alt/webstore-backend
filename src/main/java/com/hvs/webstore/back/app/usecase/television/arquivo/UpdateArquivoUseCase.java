package com.hvs.webstore.back.app.usecase.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.UpdateArquivoCommand;
import com.hvs.webstore.back.app.output.television.arquivo.UpdateArquivoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateArquivoUseCase extends UseCase<UpdateArquivoCommand, Either<Notification, UpdateArquivoOutput>> {
}