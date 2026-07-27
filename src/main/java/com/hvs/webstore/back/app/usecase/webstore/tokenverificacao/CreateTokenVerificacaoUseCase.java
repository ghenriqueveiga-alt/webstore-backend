package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.CreateTokenVerificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.CreateTokenVerificacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateTokenVerificacaoUseCase extends UseCase<CreateTokenVerificacaoCommand, Either<Notification, CreateTokenVerificacaoOutput>> {}
