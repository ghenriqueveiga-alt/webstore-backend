package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.PatchBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.PatchBoletoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchBoletoUseCase extends UseCase<PatchBoletoCommand, Either<Notification, PatchBoletoOutput>> {}
