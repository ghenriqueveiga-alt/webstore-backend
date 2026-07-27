package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.ReadVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.ReadVariacaoProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadVariacaoProdutoUseCase extends UseCase<ReadVariacaoProdutoCommand, Either<Notification, ReadVariacaoProdutoOutput>> {}
