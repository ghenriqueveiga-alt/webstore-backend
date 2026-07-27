package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.CreateBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.CreateBoletoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateBoletoUseCase extends UseCase<CreateBoletoCommand, Either<Notification, CreateBoletoOutput>> {}
