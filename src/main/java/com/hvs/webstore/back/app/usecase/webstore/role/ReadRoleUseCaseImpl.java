package com.hvs.webstore.back.app.usecase.webstore.role;

import com.hvs.webstore.back.app.command.webstore.role.ReadRoleCommand;
import com.hvs.webstore.back.app.output.webstore.role.ReadRoleOutput;
import com.hvs.webstore.back.domain.entity.webstore.role.Role;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleId;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadRoleUseCaseImpl extends ReadRoleUseCase {

    private final RoleDomainGateway gateway;

    public ReadRoleUseCaseImpl(RoleDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadRoleOutput> execute(ReadRoleCommand aRoleCommand) {

        Optional<Role> aRoleDB = aRoleCommand.aId() != null ?
                gateway.read(RoleId.from(aRoleCommand.aId())) : gateway.readByUuid(RoleUuid.from(aRoleCommand.aUuid()));

        if (aRoleDB.isPresent())
            return Try(aRoleDB::get).toEither().bimap(Notification::create, ReadRoleOutput::from);

        var aRoleId = aRoleCommand.aId() != null ? String.valueOf(aRoleCommand.aId()) : aRoleCommand.aUuid();

        return Either.left(Notification.create(new Error("Role not found: " + aRoleId)));
    }
}
