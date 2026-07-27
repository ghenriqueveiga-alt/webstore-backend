package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.app.command.webstore.imagem.UpdateImagemCommand;
import com.hvs.webstore.back.app.output.webstore.imagem.UpdateImagemOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateImagemUseCase extends UseCase<UpdateImagemCommand, Either<Notification, UpdateImagemOutput>> {}
