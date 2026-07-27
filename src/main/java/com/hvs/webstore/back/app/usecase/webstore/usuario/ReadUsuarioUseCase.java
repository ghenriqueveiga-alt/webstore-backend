package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.ReadUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.ReadUsuarioOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadUsuarioUseCase extends UseCase<ReadUsuarioCommand, Either<Notification, ReadUsuarioOutput>> {}
