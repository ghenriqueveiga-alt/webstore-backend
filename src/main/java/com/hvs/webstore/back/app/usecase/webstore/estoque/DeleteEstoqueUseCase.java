package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.DeleteEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.DeleteEstoqueOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteEstoqueUseCase extends UseCase<DeleteEstoqueCommand, Either<Notification, DeleteEstoqueOutput>> {}
