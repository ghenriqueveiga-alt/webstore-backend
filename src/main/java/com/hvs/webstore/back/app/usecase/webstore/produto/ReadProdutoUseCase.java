package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.ReadProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.ReadProdutoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadProdutoUseCase extends UseCase<ReadProdutoCommand, Either<Notification, ReadProdutoOutput>> {
    public abstract Either<Notification, ReadProdutoOutput> execute(ReadProdutoCommand aIn);
}
