package com.hvs.webstore.back.app.usecase.webstore.historicopedido;

import com.hvs.webstore.back.app.command.webstore.historicopedido.CreateHistoricoPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.historicopedido.CreateHistoricoPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateHistoricoPedidoUseCase extends UseCase<CreateHistoricoPedidoCommand, Either<Notification, CreateHistoricoPedidoOutput>> {}
