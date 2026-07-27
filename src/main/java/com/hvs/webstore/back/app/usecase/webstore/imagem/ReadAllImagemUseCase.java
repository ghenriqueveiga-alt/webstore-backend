package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.app.command.webstore.imagem.ReadAllImagemCommand;
import com.hvs.webstore.back.app.output.webstore.imagem.ReadAllImagemOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllImagemUseCase extends UseCase<ReadAllImagemCommand, Either<Notification, ReadAllImagemOutput>> {}
