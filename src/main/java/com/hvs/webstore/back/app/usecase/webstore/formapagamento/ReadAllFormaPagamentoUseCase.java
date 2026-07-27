package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.app.command.webstore.formapagamento.ReadAllFormaPagamentoCommand;
import com.hvs.webstore.back.app.output.webstore.formapagamento.ReadAllFormaPagamentoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllFormaPagamentoUseCase extends UseCase<ReadAllFormaPagamentoCommand, Either<Notification, ReadAllFormaPagamentoOutput>> {}
