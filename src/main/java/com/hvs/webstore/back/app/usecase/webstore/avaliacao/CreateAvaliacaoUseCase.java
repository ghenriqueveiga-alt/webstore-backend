package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.app.command.webstore.avaliacao.CreateAvaliacaoCommand;
import com.hvs.webstore.back.app.output.webstore.avaliacao.CreateAvaliacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateAvaliacaoUseCase extends UseCase<CreateAvaliacaoCommand, Either<Notification, CreateAvaliacaoOutput>> {}
