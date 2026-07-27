package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.CreateCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.CreateCartaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateCartaoUseCase extends UseCase<CreateCartaoCommand, Either<Notification, CreateCartaoOutput>> {}
