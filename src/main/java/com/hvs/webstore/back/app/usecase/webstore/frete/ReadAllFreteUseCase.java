package com.hvs.webstore.back.app.usecase.webstore.frete;

import com.hvs.webstore.back.app.command.webstore.frete.ReadAllFreteCommand;
import com.hvs.webstore.back.app.output.webstore.frete.ReadAllFreteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllFreteUseCase extends UseCase<ReadAllFreteCommand, Either<Notification, ReadAllFreteOutput>> {}
