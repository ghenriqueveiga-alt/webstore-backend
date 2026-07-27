package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.CreatePermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.CreatePermissaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreatePermissaoUseCaseImpl extends CreatePermissaoUseCase {

    private final PermissaoDomainGateway gateway;

    public CreatePermissaoUseCaseImpl(PermissaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreatePermissaoOutput> execute(CreatePermissaoCommand aPermissaoCommand) {

        var notification = Notification.create();
        var permissao = Permissao.create(aPermissaoCommand.aNome(),
                                         aPermissaoCommand.aChave(),
                                         aPermissaoCommand.aDescricao());
        permissao.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(permissao);
    }

    @Transactional
    private Either<Notification, CreatePermissaoOutput> create(Permissao aPermissao) {

        return Try(() -> gateway.create(aPermissao))
                .toEither().bimap(Notification::create, CreatePermissaoOutput::from);
    }
}
