package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.app.output.webstore.listadesejos.ReadAllListaDesejosOutput;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ListarListasDesejosUseCaseImpl extends ListarListasDesejosUseCase {

    private final ListaDesejosDomainGateway gateway;

    public ListarListasDesejosUseCaseImpl(ListaDesejosDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllListaDesejosOutput> execute(Void aIn) {

        var query = SearchQuery.from("", 0, 10, "id", "asc");
        var pagination = gateway.readAll(query);

        if (!pagination.aContent().isEmpty())
            return Try(() -> gateway.readAll(query)).toEither().bimap(
                    Notification::create, (Pagination<ListaDesejos> p) -> ReadAllListaDesejosOutput.from(p.aContent()));

        return Either.left(Notification.create(new Error("No ListaDesejos was found.")));
    }
}
