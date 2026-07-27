package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.app.output.webstore.listadesejos.ListaDesejosOutput;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class VisualizarListaDesejosUseCaseImpl extends VisualizarListaDesejosUseCase {

    private final ListaDesejosDomainGateway gateway;

    public VisualizarListaDesejosUseCaseImpl(ListaDesejosDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ListaDesejosOutput> execute(String aIn) {

        Optional<ListaDesejos> aListaDesejosDB = gateway.readByUuid(ListaDesejosUuid.from(aIn));

        if (aListaDesejosDB.isPresent())
            return Try(aListaDesejosDB::get)
                    .toEither().bimap(Notification::create, ListaDesejosOutput::from);

        return Either.left(Notification.create(new Error("ListaDesejos not found: " + aIn)));
    }
}
