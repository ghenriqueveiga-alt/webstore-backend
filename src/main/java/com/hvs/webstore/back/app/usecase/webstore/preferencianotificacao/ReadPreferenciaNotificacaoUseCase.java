package com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao;

import com.hvs.webstore.back.app.command.webstore.preferencianotificacao.ReadPreferenciaNotificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.preferencianotificacao.ReadPreferenciaNotificacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadPreferenciaNotificacaoUseCase extends UseCase<ReadPreferenciaNotificacaoCommand, Either<Notification, ReadPreferenciaNotificacaoOutput>> {}
