package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.app.command.webstore.formapagamento.DeleteFormaPagamentoCommand;
import com.hvs.webstore.back.app.output.webstore.formapagamento.DeleteFormaPagamentoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteFormaPagamentoUseCase extends UseCase<DeleteFormaPagamentoCommand, Either<Notification, DeleteFormaPagamentoOutput>> {}
