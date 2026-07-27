package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.ReadPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.ReadPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadPedidoUseCase extends UseCase<ReadPedidoCommand, Either<Notification, ReadPedidoOutput>> {}
