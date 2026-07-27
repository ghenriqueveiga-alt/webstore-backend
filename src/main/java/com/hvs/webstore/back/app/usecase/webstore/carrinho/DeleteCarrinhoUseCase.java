package com.hvs.webstore.back.app.usecase.webstore.carrinho;

import com.hvs.webstore.back.app.command.webstore.carrinho.DeleteCarrinhoCommand;
import com.hvs.webstore.back.app.output.webstore.carrinho.DeleteCarrinhoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteCarrinhoUseCase extends UseCase<DeleteCarrinhoCommand, Either<Notification, DeleteCarrinhoOutput>> {}
