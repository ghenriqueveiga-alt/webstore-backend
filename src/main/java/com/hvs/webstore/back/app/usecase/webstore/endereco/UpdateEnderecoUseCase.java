package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.app.command.webstore.endereco.UpdateEnderecoCommand;
import com.hvs.webstore.back.app.output.webstore.endereco.UpdateEnderecoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateEnderecoUseCase extends UseCase<UpdateEnderecoCommand, Either<Notification, UpdateEnderecoOutput>> {}
