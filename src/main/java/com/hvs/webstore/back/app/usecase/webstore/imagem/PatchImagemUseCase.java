package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.app.command.webstore.imagem.PatchImagemCommand;
import com.hvs.webstore.back.app.output.webstore.imagem.PatchImagemOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchImagemUseCase extends UseCase<PatchImagemCommand, Either<Notification, PatchImagemOutput>> {}
