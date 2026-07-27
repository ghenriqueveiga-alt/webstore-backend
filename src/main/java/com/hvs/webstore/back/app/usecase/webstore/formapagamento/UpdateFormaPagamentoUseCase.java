package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.app.command.webstore.formapagamento.UpdateFormaPagamentoCommand;
import com.hvs.webstore.back.app.output.webstore.formapagamento.UpdateFormaPagamentoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateFormaPagamentoUseCase extends UseCase<UpdateFormaPagamentoCommand, Either<Notification, UpdateFormaPagamentoOutput>> {}
