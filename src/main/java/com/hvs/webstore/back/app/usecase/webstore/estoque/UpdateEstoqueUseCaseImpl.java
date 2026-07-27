package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.UpdateEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.UpdateEstoqueOutput;
import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueId;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateEstoqueUseCaseImpl extends UpdateEstoqueUseCase {

    private final EstoqueDomainGateway gateway;

    public UpdateEstoqueUseCaseImpl(EstoqueDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateEstoqueOutput> execute(UpdateEstoqueCommand aEstoqueCommand) {

        Optional<Estoque> aEstoqueDB = aEstoqueCommand.aId() != null ?
                gateway.read(EstoqueId.from(aEstoqueCommand.aId())) : gateway.readByUuid(EstoqueUuid.from(aEstoqueCommand.aUuid()));

        if (aEstoqueDB.isEmpty())
            return Either.left(Notification.create(new Error("Estoque not found: " + (aEstoqueCommand.aId() != null ?
                    aEstoqueCommand.aId() : aEstoqueCommand.aUuid()))));

        var notification = Notification.create();
        var estoque = Estoque.update(aEstoqueDB.get().getId().getValue(),
                                     aEstoqueDB.get().getUuid().getValue(),
                                     aEstoqueCommand.aStatusCode(),
                                     aEstoqueCommand.aProdutoId(),
                                     aEstoqueCommand.aQuantidade(),
                                     aEstoqueCommand.aReservado(),
                                     aEstoqueCommand.aQuantidadeMinima());
        estoque.validate(notification);

        return notification.hasError() ? Left(notification) : update(estoque);
    }

    @Transactional
    private Either<Notification, UpdateEstoqueOutput> update(Estoque aEstoque) {

        return Try(() -> gateway.update(aEstoque))
                .toEither().bimap(Notification::create, UpdateEstoqueOutput::from);
    }
}
