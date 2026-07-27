package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.app.command.webstore.imagem.CreateImagemCommand;
import com.hvs.webstore.back.app.output.webstore.imagem.CreateImagemOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateImagemUseCase extends UseCase<CreateImagemCommand, Either<Notification, CreateImagemOutput>> {}
