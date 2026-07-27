package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.DeletePedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.DeletePedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeletePedidoUseCase extends UseCase<DeletePedidoCommand, Either<Notification, DeletePedidoOutput>> {}
