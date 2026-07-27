package com.hvs.webstore.back.app.usecase.webstore.carrinho;

import com.hvs.webstore.back.app.command.webstore.carrinho.ClearCarrinhoCommand;
import com.hvs.webstore.back.app.output.webstore.carrinho.CarrinhoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ClearCarrinhoUseCase extends UseCase<ClearCarrinhoCommand, Either<Notification, CarrinhoOutput>> {}
