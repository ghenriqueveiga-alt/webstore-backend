package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.DeletePermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.DeletePermissaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoId;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeletePermissaoUseCaseImpl extends DeletePermissaoUseCase {

    private final PermissaoDomainGateway gateway;

    public DeletePermissaoUseCaseImpl(PermissaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeletePermissaoOutput> execute(DeletePermissaoCommand aPermissaoCommand) {

        Optional<Permissao> aPermissaoDB = aPermissaoCommand.aId() != null ?
                gateway.read(PermissaoId.from(aPermissaoCommand.aId())) : gateway.readByUuid(PermissaoUuid.from(aPermissaoCommand.aUuid()));

        if (aPermissaoDB.isEmpty())
            return Either.left(Notification.create(new Error("Permissao not found: " + (aPermissaoCommand.aId() != null ?
                    aPermissaoCommand.aId() : aPermissaoCommand.aUuid()))));

        return delete(aPermissaoDB.get());
    }

    @Transactional
    private Either<Notification, DeletePermissaoOutput> delete(Permissao aPermissao) {

        return Try(() -> {
            gateway.delete(aPermissao);
            return DeletePermissaoOutput.from(aPermissao);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
