package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.ReadAllUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.ReadAllUsuarioOutput;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllUsuarioUseCaseImpl extends ReadAllUsuarioUseCase {

    private final UsuarioDomainGateway gateway;

    public ReadAllUsuarioUseCaseImpl(UsuarioDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllUsuarioOutput> execute(ReadAllUsuarioCommand aUsuarioCommand) {

        var usuarioPagination = gateway.readAll(aUsuarioCommand.aSearchQuery());
        var lista = usuarioPagination.aContent()
                .stream().filter(usuario -> usuario.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty())
            return Try(() -> gateway.readAll(aUsuarioCommand.aSearchQuery())).toEither().bimap(Notification::create, ReadAllUsuarioOutput::from);

        return Either.left(Notification.create(new Error("No Usuario was found.")));
    }
}
