package com.hvs.webstore.back.app.usecase.webstore.frete;

import com.hvs.webstore.back.app.command.webstore.frete.ReadFreteCommand;
import com.hvs.webstore.back.app.output.webstore.frete.ReadFreteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadFreteUseCase extends UseCase<ReadFreteCommand, Either<Notification, ReadFreteOutput>> {}
