package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.app.command.webstore.listadesejos.AdicionarItemListaCommand;
import com.hvs.webstore.back.app.output.webstore.listadesejos.AdicionarItemListaOutput;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class AdicionarItemListaUseCaseImpl extends AdicionarItemListaUseCase {

    private final ListaDesejosDomainGateway gateway;

    public AdicionarItemListaUseCaseImpl(ListaDesejosDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, AdicionarItemListaOutput> execute(AdicionarItemListaCommand aListaDesejosCommand) {

        var aListaDesejosDB = gateway.readByUuid(ListaDesejosUuid.from(aListaDesejosCommand.listaUuid()));

        if (aListaDesejosDB.isPresent()) {
            var listaAtualizada = aListaDesejosDB.get().adicionarItem(aListaDesejosCommand.produtoId());

            return adicionar(listaAtualizada);
        }

        return Either.left(Notification.create(new Error("ListaDesejos not found: " + aListaDesejosCommand.listaUuid())));
    }

    @Transactional
    private Either<Notification, AdicionarItemListaOutput> adicionar(ListaDesejos aListaDesejos) {

        var itens = aListaDesejos.getItems();
        var ultimoItem = itens.get(itens.size() - 1);

        return Try(() -> gateway.update(aListaDesejos))
                .toEither().bimap(Notification::create, lista -> AdicionarItemListaOutput.from(lista, ultimoItem));
    }
}
