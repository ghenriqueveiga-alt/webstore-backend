package com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao;

import com.hvs.webstore.back.app.command.webstore.preferencianotificacao.PatchPreferenciaNotificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.preferencianotificacao.PatchPreferenciaNotificacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchPreferenciaNotificacaoUseCase extends UseCase<PatchPreferenciaNotificacaoCommand, Either<Notification, PatchPreferenciaNotificacaoOutput>> {}
