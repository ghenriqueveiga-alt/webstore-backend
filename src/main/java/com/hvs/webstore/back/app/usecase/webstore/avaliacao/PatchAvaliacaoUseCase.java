package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.app.command.webstore.avaliacao.PatchAvaliacaoCommand;
import com.hvs.webstore.back.app.output.webstore.avaliacao.PatchAvaliacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchAvaliacaoUseCase extends UseCase<PatchAvaliacaoCommand, Either<Notification, PatchAvaliacaoOutput>> {}
