package com.hvs.ws.back.app.usecase.arquivo;

import com.hvs.ws.back.app.command.arquivo.UpdateArquivoCommand;
import com.hvs.ws.back.app.output.arquivo.UpdateArquivoOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateArquivoUseCase extends UseCase<UpdateArquivoCommand, Either<Notification, UpdateArquivoOutput>> {
}