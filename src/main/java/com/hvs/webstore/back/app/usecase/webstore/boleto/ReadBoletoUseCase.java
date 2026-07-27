package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.ReadBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.ReadBoletoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadBoletoUseCase extends UseCase<ReadBoletoCommand, Either<Notification, ReadBoletoOutput>> {}
