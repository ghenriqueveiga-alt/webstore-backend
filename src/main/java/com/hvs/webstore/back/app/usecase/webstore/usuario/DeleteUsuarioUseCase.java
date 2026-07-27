package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.DeleteUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.DeleteUsuarioOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteUsuarioUseCase extends UseCase<DeleteUsuarioCommand, Either<Notification, DeleteUsuarioOutput>> {}
