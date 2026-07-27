package com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao;

import com.hvs.webstore.back.app.command.webstore.preferencianotificacao.CreatePreferenciaNotificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.preferencianotificacao.CreatePreferenciaNotificacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreatePreferenciaNotificacaoUseCase extends UseCase<CreatePreferenciaNotificacaoCommand, Either<Notification, CreatePreferenciaNotificacaoOutput>> {}
