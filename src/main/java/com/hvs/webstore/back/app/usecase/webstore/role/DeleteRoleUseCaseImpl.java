package com.hvs.webstore.back.app.usecase.webstore.role;

import com.hvs.webstore.back.app.command.webstore.role.DeleteRoleCommand;
import com.hvs.webstore.back.app.output.webstore.role.DeleteRoleOutput;
import com.hvs.webstore.back.domain.entity.webstore.role.Role;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleId;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteRoleUseCaseImpl extends DeleteRoleUseCase {

    private final RoleDomainGateway gateway;

    public DeleteRoleUseCaseImpl(RoleDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteRoleOutput> execute(DeleteRoleCommand aRoleCommand) {

        Optional<Role> aRoleDB = aRoleCommand.aId() != null ?
                gateway.read(RoleId.from(aRoleCommand.aId())) : gateway.readByUuid(RoleUuid.from(aRoleCommand.aUuid()));

        if (aRoleDB.isEmpty())
            return Either.left(Notification.create(new Error("Role not found: " + (aRoleCommand.aId() != null ?
                    aRoleCommand.aId() : aRoleCommand.aUuid()))));

        return delete(aRoleDB.get());
    }

    @Transactional
    private Either<Notification, DeleteRoleOutput> delete(Role aRole) {

        return Try(() -> {
            gateway.delete(aRole);
            return DeleteRoleOutput.from(aRole);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
