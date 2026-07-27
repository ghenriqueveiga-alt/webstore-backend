package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.UpdateUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.UpdateUsuarioOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateUsuarioUseCase extends UseCase<UpdateUsuarioCommand, Either<Notification, UpdateUsuarioOutput>> {}
