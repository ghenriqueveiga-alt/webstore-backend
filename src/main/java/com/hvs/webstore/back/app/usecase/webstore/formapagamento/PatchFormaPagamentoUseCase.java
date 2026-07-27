package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.app.command.webstore.formapagamento.PatchFormaPagamentoCommand;
import com.hvs.webstore.back.app.output.webstore.formapagamento.PatchFormaPagamentoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchFormaPagamentoUseCase extends UseCase<PatchFormaPagamentoCommand, Either<Notification, PatchFormaPagamentoOutput>> {}
