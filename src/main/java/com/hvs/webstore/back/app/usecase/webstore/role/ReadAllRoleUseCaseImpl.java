package com.hvs.webstore.back.app.usecase.webstore.role;

import com.hvs.webstore.back.app.command.webstore.role.ReadAllRoleCommand;
import com.hvs.webstore.back.app.output.webstore.role.ReadAllRoleOutput;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllRoleUseCaseImpl extends ReadAllRoleUseCase {

    private final RoleDomainGateway gateway;

    public ReadAllRoleUseCaseImpl(RoleDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllRoleOutput> execute(ReadAllRoleCommand aRoleCommand) {

        return Try(() -> gateway.readAll(aRoleCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllRoleOutput::from);
    }
}
