package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.ReadPermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.ReadPermissaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoId;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadPermissaoUseCaseImpl extends ReadPermissaoUseCase {

    private final PermissaoDomainGateway gateway;

    public ReadPermissaoUseCaseImpl(PermissaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadPermissaoOutput> execute(ReadPermissaoCommand aPermissaoCommand) {

        Optional<Permissao> aPermissaoDB = aPermissaoCommand.aId() != null ?
                gateway.read(PermissaoId.from(aPermissaoCommand.aId())) : gateway.readByUuid(PermissaoUuid.from(aPermissaoCommand.aUuid()));

        if (aPermissaoDB.isPresent())
            return Try(aPermissaoDB::get).toEither().bimap(Notification::create, ReadPermissaoOutput::from);

        var aPermissaoId = aPermissaoCommand.aId() != null ? String.valueOf(aPermissaoCommand.aId()) : aPermissaoCommand.aUuid();

        return Either.left(Notification.create(new Error("Permissao not found: " + aPermissaoId)));
    }
}
