package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.ReadAllTokenVerificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.ReadAllTokenVerificacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllTokenVerificacaoUseCase extends UseCase<ReadAllTokenVerificacaoCommand, Either<Notification, ReadAllTokenVerificacaoOutput>> {}
