package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.app.command.webstore.avaliacao.ReadAvaliacaoCommand;
import com.hvs.webstore.back.app.output.webstore.avaliacao.AvaliacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAvaliacaoUseCase extends UseCase<ReadAvaliacaoCommand, Either<Notification, AvaliacaoOutput>> {}
