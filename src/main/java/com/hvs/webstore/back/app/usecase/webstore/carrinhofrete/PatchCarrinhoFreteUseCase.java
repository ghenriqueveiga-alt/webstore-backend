package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.PatchCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.PatchCarrinhoFreteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchCarrinhoFreteUseCase extends UseCase<PatchCarrinhoFreteCommand, Either<Notification, PatchCarrinhoFreteOutput>> {}
