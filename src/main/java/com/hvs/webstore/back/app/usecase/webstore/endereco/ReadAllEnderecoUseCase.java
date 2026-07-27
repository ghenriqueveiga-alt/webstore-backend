package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.app.command.webstore.endereco.ReadAllEnderecoCommand;
import com.hvs.webstore.back.app.output.webstore.endereco.ReadAllEnderecoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllEnderecoUseCase extends UseCase<ReadAllEnderecoCommand, Either<Notification, ReadAllEnderecoOutput>> {}
