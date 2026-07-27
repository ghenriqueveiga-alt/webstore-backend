package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.CreateUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.CreateUsuarioOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateUsuarioUseCase extends UseCase<CreateUsuarioCommand, Either<Notification, CreateUsuarioOutput>> {}
