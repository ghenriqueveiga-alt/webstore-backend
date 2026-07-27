package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.PatchEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.PatchEstoqueOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchEstoqueUseCase extends UseCase<PatchEstoqueCommand, Either<Notification, PatchEstoqueOutput>> {}
