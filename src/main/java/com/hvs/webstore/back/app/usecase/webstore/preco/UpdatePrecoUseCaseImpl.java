package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.UpdatePrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.UpdatePrecoOutput;
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

public class UpdatePrecoUseCaseImpl extends UpdatePrecoUseCase {

    private final PrecoDomainGateway gateway;

    public UpdatePrecoUseCaseImpl(PrecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdatePrecoOutput> execute(UpdatePrecoCommand aPrecoCommand) {

        Optional<Preco> aPrecoDB = aPrecoCommand.aId() != null ?
                gateway.read(PrecoId.from(aPrecoCommand.aId())) : gateway.readByUuid(PrecoUuid.from(aPrecoCommand.aUuid()));

        if (aPrecoDB.isEmpty())
            return Left(Notification.create(new Error("Preco not found: " + (aPrecoCommand.aId() != null ?
                    aPrecoCommand.aId() : aPrecoCommand.aUuid()))));

        var notification = Notification.create();
        var preco = Preco.update(aPrecoDB.get().getId().getValue(),
                                 aPrecoDB.get().getUuid().getValue(),
                                 aPrecoCommand.aStatusCode(),
                                 aPrecoCommand.aValor(),
                                 aPrecoCommand.aTipoPagamento(),
                                 aPrecoCommand.aQtdVezesParcelamento(),
                                 aPrecoCommand.aValorParcela(),
                                 aPrecoCommand.aValorTotalParcelamento(),
                                 null);
        preco.validate(notification);

        return notification.hasError() ? Left(notification) : update(preco);
    }

    @Transactional
    private Either<Notification, UpdatePrecoOutput> update(Preco aPreco) {

        return Try(() -> gateway.update(aPreco))
                .toEither().bimap(Notification::create, UpdatePrecoOutput::from);
    }
}
