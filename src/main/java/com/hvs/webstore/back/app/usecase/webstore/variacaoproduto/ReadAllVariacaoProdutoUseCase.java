package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.ReadAllVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.ReadAllVariacaoProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllVariacaoProdutoUseCase extends UseCase<ReadAllVariacaoProdutoCommand, Either<Notification, ReadAllVariacaoProdutoOutput>> {}
