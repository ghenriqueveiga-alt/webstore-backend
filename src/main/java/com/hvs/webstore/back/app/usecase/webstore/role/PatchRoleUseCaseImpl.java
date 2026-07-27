package com.hvs.webstore.back.app.usecase.webstore.role;

import com.hvs.webstore.back.app.command.webstore.role.PatchRoleCommand;
import com.hvs.webstore.back.app.output.webstore.role.PatchRoleOutput;
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

public class PatchRoleUseCaseImpl extends PatchRoleUseCase {

    private final RoleDomainGateway gateway;

    public PatchRoleUseCaseImpl(RoleDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchRoleOutput> execute(PatchRoleCommand aRoleCommand) {

        Optional<Role> aRoleDB = aRoleCommand.aId() != null ?
                gateway.read(RoleId.from(aRoleCommand.aId())) : gateway.readByUuid(RoleUuid.from(aRoleCommand.aUuid()));

        if (aRoleDB.isEmpty())
            return Left(Notification.create(new Error("Role not found: " + (aRoleCommand.aId() != null ?
                    aRoleCommand.aId() : aRoleCommand.aUuid()))));

        var notification = Notification.create();
        var role = Role.patch(aRoleCommand.aStatusCode(),
                              aRoleCommand.aNome(),
                              aRoleCommand.aDescricao(),
                              aRoleCommand.aPermissaoIds(),
                              aRoleDB.get());
        role.validate(notification);

        return notification.hasError() ? Left(notification) : patch(role);
    }
    @Transactional
    private Either<Notification, PatchRoleOutput> patch(Role aRole) {

        return Try(() -> gateway.patch(aRole))
                .toEither().bimap(Notification::create, PatchRoleOutput::from);
    }
}
