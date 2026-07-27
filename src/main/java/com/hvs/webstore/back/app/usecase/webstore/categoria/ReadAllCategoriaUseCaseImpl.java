package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.app.command.webstore.categoria.ReadAllCategoriaCommand;
import com.hvs.webstore.back.app.output.webstore.categoria.ReadAllCategoriaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllCategoriaUseCaseImpl extends ReadAllCategoriaUseCase {

    private final CategoriaDomainGateway gateway;

    public ReadAllCategoriaUseCaseImpl(CategoriaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCategoriaOutput> execute(ReadAllCategoriaCommand aCategoriaCommand) {

        Pagination<Categoria> categoriaPagination = gateway.readAll(aCategoriaCommand.aSearchQuery());
        List<Categoria> lista = categoriaPagination.aContent()
                .stream().filter(corte -> corte.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty())
            return Try(() -> gateway.readAll(aCategoriaCommand.aSearchQuery())).toEither().bimap(Notification::create, ReadAllCategoriaOutput::from);

        return Either.left(Notification.create(new Error("No Categoria was found.")));
    }
}
