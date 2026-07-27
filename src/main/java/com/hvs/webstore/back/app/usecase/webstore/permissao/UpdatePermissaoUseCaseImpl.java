package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.UpdatePermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.UpdatePermissaoOutput;
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

public class UpdatePermissaoUseCaseImpl extends UpdatePermissaoUseCase {

    private final PermissaoDomainGateway gateway;

    public UpdatePermissaoUseCaseImpl(PermissaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdatePermissaoOutput> execute(UpdatePermissaoCommand aPermissaoCommand) {

        Optional<Permissao> aPermissaoDB = aPermissaoCommand.aId() != null ?
                gateway.read(PermissaoId.from(aPermissaoCommand.aId())) : gateway.readByUuid(PermissaoUuid.from(aPermissaoCommand.aUuid()));

        if (aPermissaoDB.isEmpty())
            return Left(Notification.create(new Error("Permissao not found: " + (aPermissaoCommand.aId() != null ?
                    aPermissaoCommand.aId() : aPermissaoCommand.aUuid()))));

        var notification = Notification.create();
        var permissao = Permissao.update(aPermissaoDB.get().getId().getValue(),
                                         aPermissaoDB.get().getUuid().getValue(),
                                         aPermissaoCommand.aStatusCode(),
                                         aPermissaoCommand.aNome(),
                                         aPermissaoCommand.aChave(),
                                         aPermissaoCommand.aDescricao());
        permissao.validate(notification);

        return notification.hasError() ? Left(notification) : update(permissao);
    }
    @Transactional
    private Either<Notification, UpdatePermissaoOutput> update(Permissao aPermissao) {

        return Try(() -> gateway.update(aPermissao))
                .toEither().bimap(Notification::create, UpdatePermissaoOutput::from);
    }
}
