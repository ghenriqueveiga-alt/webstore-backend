package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.PatchTokenVerificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.PatchTokenVerificacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchTokenVerificacaoUseCase extends UseCase<PatchTokenVerificacaoCommand, Either<Notification, PatchTokenVerificacaoOutput>> {}
