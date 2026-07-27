package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.PatchUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.PatchUsuarioOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchUsuarioUseCase extends UseCase<PatchUsuarioCommand, Either<Notification, PatchUsuarioOutput>> {}
