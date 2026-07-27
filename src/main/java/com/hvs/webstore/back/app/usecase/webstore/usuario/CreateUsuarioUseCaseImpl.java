package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.app.command.webstore.usuario.CreateUsuarioCommand;
import com.hvs.webstore.back.app.output.webstore.usuario.CreateUsuarioOutput;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateUsuarioUseCaseImpl extends CreateUsuarioUseCase {

    private final UsuarioDomainGateway gateway;

    public CreateUsuarioUseCaseImpl(UsuarioDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateUsuarioOutput> execute(CreateUsuarioCommand aUsuarioCommand) {

        var notification = Notification.create();
        var usuario = Usuario.create(aUsuarioCommand.aNome(),
                                     aUsuarioCommand.aEmail(),
                                     aUsuarioCommand.aSenha(),
                                     aUsuarioCommand.aTelefone(),
                                     null);
        usuario.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(usuario);
    }

    @Transactional
    private Either<Notification, CreateUsuarioOutput> create(Usuario aUsuario) {

        return Try(() -> gateway.create(aUsuario)).toEither().bimap(Notification::create, CreateUsuarioOutput::from);
    }
}
