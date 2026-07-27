package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.ReadAllCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.ReadAllCarrinhoFreteOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllCarrinhoFreteUseCase extends UseCase<ReadAllCarrinhoFreteCommand, Either<Notification, ReadAllCarrinhoFreteOutput>> {}
