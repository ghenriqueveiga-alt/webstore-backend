package com.hvs.webstore.back.app.usecase.webstore.role;

import com.hvs.webstore.back.app.command.webstore.role.UpdateRoleCommand;
import com.hvs.webstore.back.app.output.webstore.role.UpdateRoleOutput;
import com.hvs.webstore.back.domain.entity.webstore.role.Role;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleId;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateRoleUseCaseImpl extends UpdateRoleUseCase {

    private final RoleDomainGateway gateway;

    public UpdateRoleUseCaseImpl(RoleDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateRoleOutput> execute(UpdateRoleCommand aRoleCommand) {

        Optional<Role> aRoleDB = aRoleCommand.aId() != null ?
                gateway.read(RoleId.from(aRoleCommand.aId())) : gateway.readByUuid(RoleUuid.from(aRoleCommand.aUuid()));

        if (aRoleDB.isEmpty())
            return Left(Notification.create(new Error("Role not found: " + (aRoleCommand.aId() != null ?
                    aRoleCommand.aId() : aRoleCommand.aUuid()))));

        var notification = Notification.create();
        var role = Role.update(aRoleDB.get().getId().getValue(),
                               aRoleDB.get().getUuid().getValue(),
                               aRoleCommand.aStatusCode(),
                               aRoleCommand.aNome(),
                               aRoleCommand.aDescricao(),
                               aRoleCommand.aPermissaoIds());
        role.validate(notification);

        return notification.hasError() ? Left(notification) : update(role);
    }

    @Transactional
    private Either<Notification, UpdateRoleOutput> update(Role aRole) {

        return Try(() -> gateway.update(aRole))
                .toEither().bimap(Notification::create, UpdateRoleOutput::from);
    }
}
