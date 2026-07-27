package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.DeleteCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.DeleteCartaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteCartaoUseCase extends UseCase<DeleteCartaoCommand, Either<Notification, DeleteCartaoOutput>> {}
