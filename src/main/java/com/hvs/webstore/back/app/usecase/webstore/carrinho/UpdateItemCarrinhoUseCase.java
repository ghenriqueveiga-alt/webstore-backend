package com.hvs.webstore.back.app.usecase.webstore.carrinho;

import com.hvs.webstore.back.app.command.webstore.carrinho.UpdateItemCarrinhoCommand;
import com.hvs.webstore.back.app.output.webstore.carrinho.CarrinhoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateItemCarrinhoUseCase extends UseCase<UpdateItemCarrinhoCommand, Either<Notification, CarrinhoOutput>> {}
