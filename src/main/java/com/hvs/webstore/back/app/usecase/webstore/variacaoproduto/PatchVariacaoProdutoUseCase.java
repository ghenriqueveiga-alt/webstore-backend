package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.PatchVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.PatchVariacaoProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchVariacaoProdutoUseCase extends UseCase<PatchVariacaoProdutoCommand, Either<Notification, PatchVariacaoProdutoOutput>> {}
