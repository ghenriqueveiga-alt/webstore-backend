package com.hvs.webstore.back.app.usecase.webstore.role;

import com.hvs.webstore.back.app.command.webstore.role.CreateRoleCommand;
import com.hvs.webstore.back.app.output.webstore.role.CreateRoleOutput;
import com.hvs.webstore.back.domain.entity.webstore.role.Role;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateRoleUseCaseImpl extends CreateRoleUseCase {

    private final RoleDomainGateway gateway;

    public CreateRoleUseCaseImpl(RoleDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateRoleOutput> execute(CreateRoleCommand aRoleCommand) {

        var notification = Notification.create();
        var role = Role.create(aRoleCommand.aNome(),
                               aRoleCommand.aDescricao(),
                               aRoleCommand.aPermissaoIds());
        role.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(role);
    }

    @Transactional
    private Either<Notification, CreateRoleOutput> create(Role aRole) {

        return Try(() -> gateway.create(aRole))
                .toEither().bimap(Notification::create, CreateRoleOutput::from);
    }
}
