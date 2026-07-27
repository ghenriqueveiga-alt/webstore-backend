package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.app.command.webstore.endereco.CreateEnderecoCommand;
import com.hvs.webstore.back.app.output.webstore.endereco.CreateEnderecoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateEnderecoUseCase extends UseCase<CreateEnderecoCommand, Either<Notification, CreateEnderecoOutput>> {}
