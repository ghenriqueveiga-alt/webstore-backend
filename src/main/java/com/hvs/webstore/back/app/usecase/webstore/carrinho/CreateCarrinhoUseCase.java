package com.hvs.webstore.back.app.usecase.webstore.carrinho;

import com.hvs.webstore.back.app.command.webstore.carrinho.CreateCarrinhoCommand;
import com.hvs.webstore.back.app.output.webstore.carrinho.CreateCarrinhoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateCarrinhoUseCase extends UseCase<CreateCarrinhoCommand, Either<Notification, CreateCarrinhoOutput>> {}
