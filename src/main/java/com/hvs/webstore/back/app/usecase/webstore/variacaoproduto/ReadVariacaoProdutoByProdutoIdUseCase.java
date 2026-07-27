package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.ReadVariacaoProdutoByProdutoIdCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.ReadAllVariacaoProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadVariacaoProdutoByProdutoIdUseCase extends UseCase<ReadVariacaoProdutoByProdutoIdCommand, Either<Notification, ReadAllVariacaoProdutoOutput>> {}
