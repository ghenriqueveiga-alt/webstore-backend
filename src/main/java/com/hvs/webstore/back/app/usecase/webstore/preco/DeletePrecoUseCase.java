package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.DeletePrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.DeletePrecoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeletePrecoUseCase extends UseCase<DeletePrecoCommand, Either<Notification, DeletePrecoOutput>> {}
