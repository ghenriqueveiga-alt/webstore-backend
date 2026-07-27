package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.app.command.webstore.endereco.PatchEnderecoCommand;
import com.hvs.webstore.back.app.output.webstore.endereco.PatchEnderecoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchEnderecoUseCase extends UseCase<PatchEnderecoCommand, Either<Notification, PatchEnderecoOutput>> {}
