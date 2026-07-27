package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.ReadTokenVerificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.ReadTokenVerificacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadTokenVerificacaoUseCase extends UseCase<ReadTokenVerificacaoCommand, Either<Notification, ReadTokenVerificacaoOutput>> {}
