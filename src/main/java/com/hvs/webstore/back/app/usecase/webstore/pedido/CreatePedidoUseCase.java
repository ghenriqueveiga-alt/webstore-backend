package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.CreatePedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.CreatePedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreatePedidoUseCase extends UseCase<CreatePedidoCommand, Either<Notification, CreatePedidoOutput>> {}
