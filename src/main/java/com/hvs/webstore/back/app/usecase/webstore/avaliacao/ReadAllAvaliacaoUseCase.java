package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.app.command.webstore.avaliacao.ReadAllAvaliacaoCommand;
import com.hvs.webstore.back.app.output.webstore.avaliacao.ReadAllAvaliacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllAvaliacaoUseCase extends UseCase<ReadAllAvaliacaoCommand, Either<Notification, ReadAllAvaliacaoOutput>> {}
