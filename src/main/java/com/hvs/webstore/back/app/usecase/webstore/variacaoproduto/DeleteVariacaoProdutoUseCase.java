package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.DeleteVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.DeleteVariacaoProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteVariacaoProdutoUseCase extends UseCase<DeleteVariacaoProdutoCommand, Either<Notification, DeleteVariacaoProdutoOutput>> {}
