package com.hvs.webstore.back.app.usecase.webstore.frete;

import com.hvs.webstore.back.app.command.webstore.frete.CalcularFreteCommand;
import com.hvs.webstore.back.app.output.webstore.frete.CalcularFreteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CalcularFreteUseCase extends UseCase<CalcularFreteCommand, Either<Notification, CalcularFreteOutput>> {}
