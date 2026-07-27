package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.UpdatePedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.UpdatePedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdatePedidoUseCase extends UseCase<UpdatePedidoCommand, Either<Notification, UpdatePedidoOutput>> {}
