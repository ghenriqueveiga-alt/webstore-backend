package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.app.output.webstore.listadesejos.CriarListaDesejosOutput;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeletarListaDesejosUseCaseImpl extends DeletarListaDesejosUseCase {

    private final ListaDesejosDomainGateway gateway;

    public DeletarListaDesejosUseCaseImpl(ListaDesejosDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CriarListaDesejosOutput> execute(String aIn) {

        Optional<ListaDesejos> aListaDesejosDB = gateway.readByUuid(ListaDesejosUuid.from(aIn));

        if (aListaDesejosDB.isEmpty())
            return Either.left(Notification.create(new Error("ListaDesejos not found: " + aIn)));

        return delete(aListaDesejosDB.get());
    }

    @Transactional
    private Either<Notification, CriarListaDesejosOutput> delete(ListaDesejos aListaDesejos) {

        return Try(() -> {
            gateway.delete(aListaDesejos);
            return CriarListaDesejosOutput.from(aListaDesejos);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
