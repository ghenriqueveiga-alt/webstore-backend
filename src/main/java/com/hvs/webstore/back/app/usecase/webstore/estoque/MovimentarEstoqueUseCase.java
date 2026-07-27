package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.MovimentarEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.MovimentarEstoqueOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class MovimentarEstoqueUseCase extends UseCase<MovimentarEstoqueCommand, Either<Notification, MovimentarEstoqueOutput>> {}
