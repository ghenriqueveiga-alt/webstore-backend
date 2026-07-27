package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.ReadCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.ReadCupomPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadCupomPedidoUseCase extends UseCase<ReadCupomPedidoCommand, Either<Notification, ReadCupomPedidoOutput>> {}
