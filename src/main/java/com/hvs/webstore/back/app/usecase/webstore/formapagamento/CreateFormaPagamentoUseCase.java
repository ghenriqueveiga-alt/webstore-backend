package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.app.command.webstore.formapagamento.CreateFormaPagamentoCommand;
import com.hvs.webstore.back.app.output.webstore.formapagamento.CreateFormaPagamentoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateFormaPagamentoUseCase extends UseCase<CreateFormaPagamentoCommand, Either<Notification, CreateFormaPagamentoOutput>> {}
