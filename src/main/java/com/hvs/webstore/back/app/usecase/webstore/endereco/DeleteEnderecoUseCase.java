package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.app.command.webstore.endereco.DeleteEnderecoCommand;
import com.hvs.webstore.back.app.output.webstore.endereco.DeleteEnderecoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteEnderecoUseCase extends UseCase<DeleteEnderecoCommand, Either<Notification, DeleteEnderecoOutput>> {}
