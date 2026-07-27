package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.PatchEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.PatchEstoqueOutput;
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

public class PatchEstoqueUseCaseImpl extends PatchEstoqueUseCase {

    private final EstoqueDomainGateway gateway;

    public PatchEstoqueUseCaseImpl(EstoqueDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchEstoqueOutput> execute(PatchEstoqueCommand aEstoqueCommand) {

        Optional<Estoque> aEstoqueDB = aEstoqueCommand.aId() != null ?
                gateway.read(EstoqueId.from(aEstoqueCommand.aId())) : gateway.readByUuid(EstoqueUuid.from(aEstoqueCommand.aUuid()));

        if (aEstoqueDB.isEmpty())
            return Either.left(Notification.create(new Error("Estoque not found: " + (aEstoqueCommand.aId() != null ?
                    aEstoqueCommand.aId() : aEstoqueCommand.aUuid()))));

        var notification = Notification.create();
        var estoque = Estoque.patch(aEstoqueCommand.aStatusCode(),
                                    aEstoqueCommand.aProdutoId(),
                                    aEstoqueCommand.aQuantidade(),
                                    aEstoqueCommand.aReservado(),
                                    aEstoqueCommand.aQuantidadeMinima(),
                                    aEstoqueDB.get());
        estoque.validate(notification);

        return notification.hasError() ? Left(notification) : patch(estoque);
    }

    @Transactional
    private Either<Notification, PatchEstoqueOutput> patch(Estoque aEstoque) {

        return Try(() -> gateway.patch(aEstoque))
                .toEither().bimap(Notification::create, PatchEstoqueOutput::from);
    }
}
