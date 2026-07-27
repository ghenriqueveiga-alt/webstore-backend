package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.UpdateTokenVerificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.UpdateTokenVerificacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateTokenVerificacaoUseCase extends UseCase<UpdateTokenVerificacaoCommand, Either<Notification, UpdateTokenVerificacaoOutput>> {}
