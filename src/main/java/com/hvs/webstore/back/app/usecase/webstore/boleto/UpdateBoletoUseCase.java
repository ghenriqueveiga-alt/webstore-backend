package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.UpdateBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.UpdateBoletoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateBoletoUseCase extends UseCase<UpdateBoletoCommand, Either<Notification, UpdateBoletoOutput>> {}
