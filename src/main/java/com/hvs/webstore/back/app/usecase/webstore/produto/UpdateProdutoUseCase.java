package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.UpdateProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.UpdateProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateProdutoUseCase extends UseCase<UpdateProdutoCommand, Either<Notification, UpdateProdutoOutput>> {}
