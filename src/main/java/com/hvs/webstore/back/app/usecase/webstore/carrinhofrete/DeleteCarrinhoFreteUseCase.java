package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.DeleteCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.DeleteCarrinhoFreteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteCarrinhoFreteUseCase extends UseCase<DeleteCarrinhoFreteCommand, Either<Notification, DeleteCarrinhoFreteOutput>> {}
