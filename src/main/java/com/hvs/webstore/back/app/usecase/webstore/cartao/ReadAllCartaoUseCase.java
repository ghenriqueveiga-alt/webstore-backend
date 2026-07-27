package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.ReadAllCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.ReadAllCartaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllCartaoUseCase extends UseCase<ReadAllCartaoCommand, Either<Notification, ReadAllCartaoOutput>> {}
