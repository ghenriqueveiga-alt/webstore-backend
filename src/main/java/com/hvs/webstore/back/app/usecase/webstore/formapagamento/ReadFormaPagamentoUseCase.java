package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.app.command.webstore.formapagamento.ReadFormaPagamentoCommand;
import com.hvs.webstore.back.app.output.webstore.formapagamento.ReadFormaPagamentoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadFormaPagamentoUseCase extends UseCase<ReadFormaPagamentoCommand, Either<Notification, ReadFormaPagamentoOutput>> {}
