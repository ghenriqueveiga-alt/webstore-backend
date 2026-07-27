package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.ReadAllEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.ReadAllEstoqueOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllEstoqueUseCase extends UseCase<ReadAllEstoqueCommand, Either<Notification, ReadAllEstoqueOutput>> {}
