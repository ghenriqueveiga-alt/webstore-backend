package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.PatchPermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.PatchPermissaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoId;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchPermissaoUseCaseImpl extends PatchPermissaoUseCase {

    private final PermissaoDomainGateway gateway;

    public PatchPermissaoUseCaseImpl(PermissaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchPermissaoOutput> execute(PatchPermissaoCommand aPermissaoCommand) {

        Optional<Permissao> aPermissaoDB = aPermissaoCommand.aId() != null ?
                gateway.read(PermissaoId.from(aPermissaoCommand.aId())) : gateway.readByUuid(PermissaoUuid.from(aPermissaoCommand.aUuid()));

        if (aPermissaoDB.isEmpty())
            return Left(Notification.create(new Error("Permissao not found: " + (aPermissaoCommand.aId() != null ?
                    aPermissaoCommand.aId() : aPermissaoCommand.aUuid()))));

        var notification = Notification.create();
        var permissao = Permissao.patch(aPermissaoCommand.aStatusCode(),
                                        aPermissaoCommand.aNome(),
                                        aPermissaoCommand.aChave(),
                                        aPermissaoCommand.aDescricao(),
                                        aPermissaoDB.get());
        permissao.validate(notification);

        return notification.hasError() ? Left(notification) : patch(permissao);
    }
    @Transactional
    private Either<Notification, PatchPermissaoOutput> patch(Permissao aPermissao) {

        return Try(() -> gateway.patch(aPermissao))
                .toEither().bimap(Notification::create, PatchPermissaoOutput::from);
    }
}
