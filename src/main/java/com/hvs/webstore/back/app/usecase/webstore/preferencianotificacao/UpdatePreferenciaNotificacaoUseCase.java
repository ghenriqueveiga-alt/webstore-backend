package com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao;

import com.hvs.webstore.back.app.command.webstore.preferencianotificacao.UpdatePreferenciaNotificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.preferencianotificacao.UpdatePreferenciaNotificacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdatePreferenciaNotificacaoUseCase extends UseCase<UpdatePreferenciaNotificacaoCommand, Either<Notification, UpdatePreferenciaNotificacaoOutput>> {}
