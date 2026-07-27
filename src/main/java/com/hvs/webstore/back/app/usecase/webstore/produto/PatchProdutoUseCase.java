package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.PatchProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.PatchProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchProdutoUseCase extends UseCase<PatchProdutoCommand, Either<Notification, PatchProdutoOutput>> {}
