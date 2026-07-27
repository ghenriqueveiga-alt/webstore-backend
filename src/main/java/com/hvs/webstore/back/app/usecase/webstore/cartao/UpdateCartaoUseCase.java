package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.UpdateCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.UpdateCartaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateCartaoUseCase extends UseCase<UpdateCartaoCommand, Either<Notification, UpdateCartaoOutput>> {}
