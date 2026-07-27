package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.PatchUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.PatchUsuarioOutput;
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

public class PatchUsuarioUseCaseImpl extends PatchUsuarioUseCase {

    private final UsuarioDomainGateway gateway;

    public PatchUsuarioUseCaseImpl(UsuarioDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchUsuarioOutput> execute(PatchUsuarioCommand aUsuarioCommand) {

        Optional<Usuario> aUsuarioDB = aUsuarioCommand.aId() != null ?
                gateway.read(UsuarioId.from(aUsuarioCommand.aId())) : gateway.readByUuid(UsuarioUuid.from(aUsuarioCommand.aUuid()));

        if (aUsuarioDB.isEmpty())
            return Left(Notification.create(new Error("Usuario not found: " + (aUsuarioCommand.aId() != null ?
                    aUsuarioCommand.aId() : aUsuarioCommand.aUuid()))));

        var notification = Notification.create();
        var usuario = Usuario.patch(aUsuarioCommand.aStatusCode(),
                                    aUsuarioCommand.aNome(),
                                    aUsuarioCommand.aEmail(),
                                    aUsuarioCommand.aSenha(),
                                    aUsuarioCommand.aTelefone(),
                                    aUsuarioCommand.aDataCadastro(),
                                    aUsuarioDB.get());
        usuario.validate(notification);

        return notification.hasError() ? Left(notification) : patch(usuario);
    }

    @Transactional
    private Either<Notification, PatchUsuarioOutput> patch(Usuario aUsuario) {

        return Try(() -> gateway.patch(aUsuario))
                .toEither().bimap(Notification::create, PatchUsuarioOutput::from);
    }
}
