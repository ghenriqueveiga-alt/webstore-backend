package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.UpdateEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.UpdateEstoqueOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateEstoqueUseCase extends UseCase<UpdateEstoqueCommand, Either<Notification, UpdateEstoqueOutput>> {}
