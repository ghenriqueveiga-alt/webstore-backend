package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.DeleteUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.DeleteUsuarioOutput;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioId;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteUsuarioUseCaseImpl extends DeleteUsuarioUseCase {

    private final UsuarioDomainGateway gateway;

    public DeleteUsuarioUseCaseImpl(UsuarioDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteUsuarioOutput> execute(DeleteUsuarioCommand aUsuarioCommand) {

        Optional<Usuario> aUsuarioDB = aUsuarioCommand.aId() != null ?
                gateway.read(UsuarioId.from(aUsuarioCommand.aId())) : gateway.readByUuid(UsuarioUuid.from(aUsuarioCommand.aUuid()));

        if (aUsuarioDB.isEmpty())
            return Either.left(Notification.create(new Error("Usuario not found: " + (aUsuarioCommand.aId() != null ?
                    aUsuarioCommand.aId() : aUsuarioCommand.aUuid()))));

        return delete(aUsuarioDB.get());
    }

    @Transactional
    private Either<Notification, DeleteUsuarioOutput> delete(Usuario aUsuario) {

        return Try(() -> {
            gateway.delete(aUsuario);
            return DeleteUsuarioOutput.from(aUsuario);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
