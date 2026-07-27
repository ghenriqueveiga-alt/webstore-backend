package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.ReadEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.ReadEstoqueOutput;
import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueId;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadEstoqueUseCaseImpl extends ReadEstoqueUseCase {

    private final EstoqueDomainGateway gateway;

    public ReadEstoqueUseCaseImpl(EstoqueDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadEstoqueOutput> execute(ReadEstoqueCommand aEstoqueCommand) {

        Optional<Estoque> aEstoqueDB = aEstoqueCommand.aId() != null ?
                gateway.read(EstoqueId.from(aEstoqueCommand.aId())) : gateway.readByUuid(EstoqueUuid.from(aEstoqueCommand.aUuid()));

        if (aEstoqueDB.isPresent())
            return Try(aEstoqueDB::get).toEither().bimap(Notification::create, ReadEstoqueOutput::from);

        var aEstoqueId = aEstoqueCommand.aId() != null ? String.valueOf(aEstoqueCommand.aId()) : aEstoqueCommand.aUuid();

        return Either.left(Notification.create(new Error("Estoque not found: " + aEstoqueId)));
    }
}
