package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.PatchPrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.PatchPrecoOutput;
import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoId;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchPrecoUseCaseImpl extends PatchPrecoUseCase {

    private final PrecoDomainGateway gateway;

    public PatchPrecoUseCaseImpl(PrecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchPrecoOutput> execute(PatchPrecoCommand aPrecoCommand) {

        Optional<Preco> aPrecoDB = aPrecoCommand.aId() != null ?
                gateway.read(PrecoId.from(aPrecoCommand.aId())) : gateway.readByUuid(PrecoUuid.from(aPrecoCommand.aUuid()));

        if (aPrecoDB.isEmpty())
            return Left(Notification.create(new Error("Preco not found: " + (aPrecoCommand.aId() != null ?
                    aPrecoCommand.aId() : aPrecoCommand.aUuid()))));

        var notification = Notification.create();
        var preco = Preco.patch(aPrecoCommand.aStatusCode(),
                                aPrecoCommand.aValor(),
                                aPrecoCommand.aTipoPagamento(),
                                aPrecoCommand.aQtdVezesParcelamento(),
                                aPrecoCommand.aValorParcela(),
                                aPrecoCommand.aValorTotalParcelamento(),
                                null,
                                aPrecoDB.get());
        preco.validate(notification);

        return notification.hasError() ? Left(notification) : patch(preco);
    }
    @Transactional
    private Either<Notification, PatchPrecoOutput> patch(Preco aPreco) {

        return Try(() -> gateway.patch(aPreco))
                .toEither().bimap(Notification::create, PatchPrecoOutput::from);
    }
}
