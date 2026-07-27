package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.CreateProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.CreateProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateProdutoUseCase extends UseCase<CreateProdutoCommand, Either<Notification, CreateProdutoOutput>> {}
