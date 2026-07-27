package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.UpdateCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.UpdateCarrinhoFreteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateCarrinhoFreteUseCase extends UseCase<UpdateCarrinhoFreteCommand, Either<Notification, UpdateCarrinhoFreteOutput>> {}
