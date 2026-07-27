package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.app.command.webstore.imagem.DeleteImagemCommand;
import com.hvs.webstore.back.app.output.webstore.imagem.DeleteImagemOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteImagemUseCase extends UseCase<DeleteImagemCommand, Either<Notification, DeleteImagemOutput>> {}
