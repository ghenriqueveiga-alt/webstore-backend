package com.hvs.webstore.back.app.usecase.webstore.historicopedido;

import com.hvs.webstore.back.app.command.webstore.historicopedido.ReadHistoricoPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.historicopedido.ReadHistoricoPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadHistoricoPedidoUseCase extends UseCase<ReadHistoricoPedidoCommand, Either<Notification, ReadHistoricoPedidoOutput>> {}
