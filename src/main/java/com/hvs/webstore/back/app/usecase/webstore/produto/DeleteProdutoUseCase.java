package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.DeleteProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.DeleteProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteProdutoUseCase extends UseCase<DeleteProdutoCommand, Either<Notification, DeleteProdutoOutput>> {}
