package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.DeleteBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.DeleteBoletoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteBoletoUseCase extends UseCase<DeleteBoletoCommand, Either<Notification, DeleteBoletoOutput>> {}
