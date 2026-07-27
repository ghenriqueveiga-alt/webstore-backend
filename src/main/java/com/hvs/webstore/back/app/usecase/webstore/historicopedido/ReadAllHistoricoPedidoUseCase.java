package com.hvs.webstore.back.app.usecase.webstore.historicopedido;

import com.hvs.webstore.back.app.command.webstore.historicopedido.ReadAllHistoricoPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.historicopedido.ReadAllHistoricoPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllHistoricoPedidoUseCase extends UseCase<ReadAllHistoricoPedidoCommand, Either<Notification, ReadAllHistoricoPedidoOutput>> {}
