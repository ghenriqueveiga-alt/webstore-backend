package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.ReadUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.ReadUsuarioOutput;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioId;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadUsuarioUseCaseImpl extends ReadUsuarioUseCase {

    private final UsuarioDomainGateway gateway;

    public ReadUsuarioUseCaseImpl(UsuarioDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadUsuarioOutput> execute(ReadUsuarioCommand aUsuarioCommand) {

        Optional<Usuario> aUsuarioDB = aUsuarioCommand.aId() != null ?
                gateway.read(UsuarioId.from(aUsuarioCommand.aId())) : gateway.readByUuid(UsuarioUuid.from(aUsuarioCommand.aUuid()));

        if (aUsuarioDB.isPresent())
            return Try(aUsuarioDB::get).toEither().bimap(Notification::create, ReadUsuarioOutput::from);

        var aUsuarioId = aUsuarioCommand.aId() != null ? String.valueOf(aUsuarioCommand.aId()) : aUsuarioCommand.aUuid();

        return Either.left(Notification.create(new Error("Usuario not found: " + aUsuarioId)));
    }
}
