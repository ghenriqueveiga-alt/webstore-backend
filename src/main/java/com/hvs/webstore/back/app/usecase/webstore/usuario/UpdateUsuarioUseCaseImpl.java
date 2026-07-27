package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.UpdateUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.UpdateUsuarioOutput;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioId;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateUsuarioUseCaseImpl extends UpdateUsuarioUseCase {

    private final UsuarioDomainGateway gateway;

    public UpdateUsuarioUseCaseImpl(UsuarioDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateUsuarioOutput> execute(UpdateUsuarioCommand aUsuarioCommand) {

        Optional<Usuario> aUsuarioDB = aUsuarioCommand.aId() != null ?
                gateway.read(UsuarioId.from(aUsuarioCommand.aId())) : gateway.readByUuid(UsuarioUuid.from(aUsuarioCommand.aUuid()));

        if (aUsuarioDB.isEmpty())
            return Left(Notification.create(new Error("Usuario not found: " + (aUsuarioCommand.aId() != null ?
                    aUsuarioCommand.aId() : aUsuarioCommand.aUuid()))));

        var notification = Notification.create();
        var usuario = Usuario.update(aUsuarioDB.get().getId().getValue(),
                                     aUsuarioDB.get().getUuid().getValue(),
                                     aUsuarioCommand.aStatusCode(),
                                     aUsuarioCommand.aNome(),
                                     aUsuarioCommand.aEmail(),
                                     aUsuarioCommand.aSenha(),
                                     aUsuarioCommand.aTelefone(),
                                     aUsuarioCommand.aDataCadastro());
        usuario.validate(notification);

        return notification.hasError() ? Left(notification) : update(usuario);
    }

    @Transactional
    private Either<Notification, UpdateUsuarioOutput> update(Usuario aUsuario) {

        return Try(() -> gateway.update(aUsuario))
                .toEither().bimap(Notification::create, UpdateUsuarioOutput::from);
    }
}
