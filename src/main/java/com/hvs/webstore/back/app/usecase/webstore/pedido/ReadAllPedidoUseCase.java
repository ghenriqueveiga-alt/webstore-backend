package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.ReadAllPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.ReadAllPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllPedidoUseCase extends UseCase<ReadAllPedidoCommand, Either<Notification, ReadAllPedidoOutput>> {}
