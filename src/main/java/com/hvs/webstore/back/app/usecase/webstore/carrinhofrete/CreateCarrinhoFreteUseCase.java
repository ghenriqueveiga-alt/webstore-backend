package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.CreateCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.CreateCarrinhoFreteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateCarrinhoFreteUseCase extends UseCase<CreateCarrinhoFreteCommand, Either<Notification, CreateCarrinhoFreteOutput>> {}
