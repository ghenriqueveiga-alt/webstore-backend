package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.app.command.webstore.listadesejos.CriarListaDesejosCommand;
import com.hvs.webstore.back.app.output.webstore.listadesejos.CriarListaDesejosOutput;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CriarListaDesejosUseCaseImpl extends CriarListaDesejosUseCase {

    private final ListaDesejosDomainGateway gateway;

    public CriarListaDesejosUseCaseImpl(ListaDesejosDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CriarListaDesejosOutput> execute(CriarListaDesejosCommand aListaDesejosCommand) {

        var notification = Notification.create();
        var listaDesejos = ListaDesejos.create(aListaDesejosCommand.usuarioId());
        listaDesejos.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(listaDesejos);
    }

    @Transactional
    private Either<Notification, CriarListaDesejosOutput> create(ListaDesejos aListaDesejos) {

        return Try(() -> gateway.create(aListaDesejos))
                .toEither().bimap(Notification::create, CriarListaDesejosOutput::from);
    }
}
