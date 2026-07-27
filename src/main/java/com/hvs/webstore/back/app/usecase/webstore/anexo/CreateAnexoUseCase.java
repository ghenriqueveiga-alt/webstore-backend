package com.hvs.webstore.back.app.usecase.webstore.anexo;

import com.hvs.webstore.back.app.command.webstore.anexo.CreateAnexoCommand;
import com.hvs.webstore.back.app.output.webstore.anexo.CreateAnexoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateAnexoUseCase extends UseCase<CreateAnexoCommand, Either<Notification, CreateAnexoOutput>> {}
