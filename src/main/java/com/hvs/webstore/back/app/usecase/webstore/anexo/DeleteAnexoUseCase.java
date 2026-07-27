package com.hvs.webstore.back.app.usecase.webstore.anexo;

import com.hvs.webstore.back.app.command.webstore.anexo.DeleteAnexoCommand;
import com.hvs.webstore.back.app.output.webstore.anexo.DeleteAnexoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteAnexoUseCase extends UseCase<DeleteAnexoCommand, Either<Notification, DeleteAnexoOutput>> {}
