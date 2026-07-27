package com.hvs.webstore.back.app.usecase.webstore.historicopedido;

import com.hvs.webstore.back.app.command.webstore.historicopedido.ReadHistoricoPedidoByPedidoIdCommand;
import com.hvs.webstore.back.app.output.webstore.historicopedido.ReadAllHistoricoPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadHistoricoPedidoByPedidoIdUseCase extends UseCase<ReadHistoricoPedidoByPedidoIdCommand, Either<Notification, ReadAllHistoricoPedidoOutput>> {}
