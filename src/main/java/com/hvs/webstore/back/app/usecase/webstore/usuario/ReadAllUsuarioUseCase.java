package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.ReadAllUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.ReadAllUsuarioOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllUsuarioUseCase extends UseCase<ReadAllUsuarioCommand, Either<Notification, ReadAllUsuarioOutput>> {}
