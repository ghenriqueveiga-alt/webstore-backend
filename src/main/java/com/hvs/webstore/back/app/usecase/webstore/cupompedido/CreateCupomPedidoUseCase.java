package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.CreateCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.CreateCupomPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateCupomPedidoUseCase extends UseCase<CreateCupomPedidoCommand, Either<Notification, CreateCupomPedidoOutput>> {}
