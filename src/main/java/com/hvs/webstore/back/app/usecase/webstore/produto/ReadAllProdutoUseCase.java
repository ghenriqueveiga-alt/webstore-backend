package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.ReadAllProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.ReadAllProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllProdutoUseCase extends UseCase<ReadAllProdutoCommand, Either<Notification, ReadAllProdutoOutput>> {}
