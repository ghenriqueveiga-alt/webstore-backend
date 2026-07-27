package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.PatchCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.PatchCupomPedidoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchCupomPedidoUseCase extends UseCase<PatchCupomPedidoCommand, Either<Notification, PatchCupomPedidoOutput>> {}
