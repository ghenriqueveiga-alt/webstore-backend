package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.ReadRootsCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.ReadAllCategoriaHierarquiaOutput;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadRootsUseCaseImpl extends ReadRootsUseCase {

    private final CategoriaHierarquiaDomainGateway gateway;

    public ReadRootsUseCaseImpl(CategoriaHierarquiaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCategoriaHierarquiaOutput> execute(ReadRootsCommand aCategoriaHierarquiaCommand) {

        Pagination<CategoriaHierarquia> pagination = gateway.readRoots(aCategoriaHierarquiaCommand.aSearchQuery());
        List<CategoriaHierarquia> lista = pagination.aContent().stream().filter(c -> c.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {
            return Try(() -> gateway.readRoots(aCategoriaHierarquiaCommand.aSearchQuery())).toEither().bimap(
                    Notification::create, ReadAllCategoriaHierarquiaOutput::from);
        } else {
            return Either.left(Notification.create(new Error("No CategoriaHierarquia root was found.")));
        }
    }
}
