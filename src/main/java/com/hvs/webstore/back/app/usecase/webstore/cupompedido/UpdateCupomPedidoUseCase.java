package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.UpdateCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.UpdateCupomPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateCupomPedidoUseCase extends UseCase<UpdateCupomPedidoCommand, Either<Notification, UpdateCupomPedidoOutput>> {}
