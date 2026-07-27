package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.ReadAllPermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.ReadAllPermissaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllPermissaoUseCaseImpl extends ReadAllPermissaoUseCase {

    private final PermissaoDomainGateway gateway;

    public ReadAllPermissaoUseCaseImpl(PermissaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllPermissaoOutput> execute(ReadAllPermissaoCommand aPermissaoCommand) {

        return Try(() -> gateway.readAll(aPermissaoCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllPermissaoOutput::from);
    }
}
