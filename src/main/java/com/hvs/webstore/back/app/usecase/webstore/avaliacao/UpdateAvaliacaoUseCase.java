package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.app.command.webstore.avaliacao.UpdateAvaliacaoCommand;
import com.hvs.webstore.back.app.output.webstore.avaliacao.UpdateAvaliacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateAvaliacaoUseCase extends UseCase<UpdateAvaliacaoCommand, Either<Notification, UpdateAvaliacaoOutput>> {}
