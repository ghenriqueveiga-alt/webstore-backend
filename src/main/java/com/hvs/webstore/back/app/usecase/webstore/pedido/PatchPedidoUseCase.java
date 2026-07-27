package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.PatchPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.PatchPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchPedidoUseCase extends UseCase<PatchPedidoCommand, Either<Notification, PatchPedidoOutput>> {}
