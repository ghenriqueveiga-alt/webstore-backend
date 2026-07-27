package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.PatchPrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.PatchPrecoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchPrecoUseCase extends UseCase<PatchPrecoCommand, Either<Notification, PatchPrecoOutput>> {}
