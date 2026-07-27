package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.ReadPrecoPromocionalByProdutoIdCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.ReadAllPrecoPromocionalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadPrecoPromocionalByProdutoIdUseCase extends UseCase<ReadPrecoPromocionalByProdutoIdCommand, Either<Notification, ReadAllPrecoPromocionalOutput>> {}
