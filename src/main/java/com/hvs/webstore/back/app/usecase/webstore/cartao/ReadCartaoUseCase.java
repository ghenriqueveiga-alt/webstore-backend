package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.ReadCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.ReadCartaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadCartaoUseCase extends UseCase<ReadCartaoCommand, Either<Notification, ReadCartaoOutput>> {}
