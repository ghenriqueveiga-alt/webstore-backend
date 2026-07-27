package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.DeleteCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.DeleteCupomPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteCupomPedidoUseCase extends UseCase<DeleteCupomPedidoCommand, Either<Notification, DeleteCupomPedidoOutput>> {}
