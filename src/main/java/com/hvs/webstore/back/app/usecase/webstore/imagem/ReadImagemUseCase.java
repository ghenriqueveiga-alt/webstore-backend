package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.app.command.webstore.imagem.ReadImagemCommand;
import com.hvs.webstore.back.app.output.webstore.imagem.ReadImagemOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadImagemUseCase extends UseCase<ReadImagemCommand, Either<Notification, ReadImagemOutput>> {}
