package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.ReadAllBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.ReadAllBoletoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllBoletoUseCase extends UseCase<ReadAllBoletoCommand, Either<Notification, ReadAllBoletoOutput>> {}
