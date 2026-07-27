package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.DeleteTokenVerificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.DeleteTokenVerificacaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteTokenVerificacaoUseCase extends UseCase<DeleteTokenVerificacaoCommand, Either<Notification, DeleteTokenVerificacaoOutput>> {}
