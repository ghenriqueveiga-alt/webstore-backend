package com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao;

import com.hvs.webstore.back.app.command.webstore.preferencianotificacao.DeletePreferenciaNotificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.preferencianotificacao.DeletePreferenciaNotificacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeletePreferenciaNotificacaoUseCase extends UseCase<DeletePreferenciaNotificacaoCommand, Either<Notification, DeletePreferenciaNotificacaoOutput>> {}
