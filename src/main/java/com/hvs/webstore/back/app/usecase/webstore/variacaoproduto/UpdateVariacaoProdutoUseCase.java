package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.UpdateVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.UpdateVariacaoProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateVariacaoProdutoUseCase extends UseCase<UpdateVariacaoProdutoCommand, Either<Notification, UpdateVariacaoProdutoOutput>> {}
