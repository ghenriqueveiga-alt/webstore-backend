package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.CreateEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.CreateEstoqueOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateEstoqueUseCase extends UseCase<CreateEstoqueCommand, Either<Notification, CreateEstoqueOutput>> {}
