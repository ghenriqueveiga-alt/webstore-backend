package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.app.command.webstore.avaliacao.DeleteAvaliacaoCommand;
import com.hvs.webstore.back.app.output.webstore.avaliacao.DeleteAvaliacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteAvaliacaoUseCase extends UseCase<DeleteAvaliacaoCommand, Either<Notification, DeleteAvaliacaoOutput>> {}
