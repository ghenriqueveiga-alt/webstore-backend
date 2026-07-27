package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.DeleteEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.DeleteEstoqueOutput;
import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueId;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteEstoqueUseCaseImpl extends DeleteEstoqueUseCase {

    private final EstoqueDomainGateway gateway;

    public DeleteEstoqueUseCaseImpl(EstoqueDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteEstoqueOutput> execute(DeleteEstoqueCommand aEstoqueCommand) {

        Optional<Estoque> aEstoqueDB = aEstoqueCommand.aId() != null ?
                gateway.read(EstoqueId.from(aEstoqueCommand.aId())) : gateway.readByUuid(EstoqueUuid.from(aEstoqueCommand.aUuid()));

        if (aEstoqueDB.isEmpty())
            return Either.left(Notification.create(new Error("Estoque not found: " + (aEstoqueCommand.aId() != null ?
                    aEstoqueCommand.aId() : aEstoqueCommand.aUuid()))));

        return delete(aEstoqueDB.get());
    }

    @Transactional
    private Either<Notification, DeleteEstoqueOutput> delete(Estoque aEstoque) {

        return Try(() -> {
            gateway.delete(aEstoque);
            return DeleteEstoqueOutput.from(aEstoque);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
