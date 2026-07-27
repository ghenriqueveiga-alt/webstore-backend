package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.ReadByPedidoIdCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.ReadAllCupomPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadByPedidoIdCupomPedidoUseCase extends UseCase<ReadByPedidoIdCupomPedidoCommand, Either<Notification, ReadAllCupomPedidoOutput>> {}
