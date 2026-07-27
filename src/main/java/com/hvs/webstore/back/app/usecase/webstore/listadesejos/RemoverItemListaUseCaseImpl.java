package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.app.command.webstore.listadesejos.RemoverItemListaCommand;
import com.hvs.webstore.back.app.output.webstore.listadesejos.RemoverItemListaOutput;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class RemoverItemListaUseCaseImpl extends RemoverItemListaUseCase {

    private final ListaDesejosDomainGateway gateway;

    public RemoverItemListaUseCaseImpl(ListaDesejosDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, RemoverItemListaOutput> execute(RemoverItemListaCommand aListaDesejosCommand) {

        var aListaDesejosDB = gateway.readByUuid(ListaDesejosUuid.from(aListaDesejosCommand.listaUuid()));

        if (aListaDesejosDB.isPresent()) {
            var listaAtualizada = aListaDesejosDB.get().removerItem(aListaDesejosCommand.itemUuid());

            return remover(listaAtualizada);
        }

        return Either.left(Notification.create(new Error("ListaDesejos not found: " + aListaDesejosCommand.listaUuid())));
    }

    @Transactional
    private Either<Notification, RemoverItemListaOutput> remover(ListaDesejos aListaDesejos) {

        return Try(() -> gateway.update(aListaDesejos))
                .toEither().bimap(Notification::create, RemoverItemListaOutput::from);
    }
}
