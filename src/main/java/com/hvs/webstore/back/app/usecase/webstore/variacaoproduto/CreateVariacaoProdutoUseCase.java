package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.CreateVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.CreateVariacaoProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateVariacaoProdutoUseCase extends UseCase<CreateVariacaoProdutoCommand, Either<Notification, CreateVariacaoProdutoOutput>> {}
