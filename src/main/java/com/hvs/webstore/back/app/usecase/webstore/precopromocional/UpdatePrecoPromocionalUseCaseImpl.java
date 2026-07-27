package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.UpdatePrecoPromocionalCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.UpdatePrecoPromocionalOutput;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalId;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdatePrecoPromocionalUseCaseImpl extends UpdatePrecoPromocionalUseCase {

    private final PrecoPromocionalDomainGateway gateway;

    public UpdatePrecoPromocionalUseCaseImpl(PrecoPromocionalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdatePrecoPromocionalOutput> execute(UpdatePrecoPromocionalCommand aPrecoPromocionalCommand) {

        Optional<PrecoPromocional> aPrecoPromocionalDB = aPrecoPromocionalCommand.aId() != null ?
                gateway.read(PrecoPromocionalId.from(aPrecoPromocionalCommand.aId())) : gateway.readByUuid(PrecoPromocionalUuid.from(aPrecoPromocionalCommand.aUuid()));

        if (aPrecoPromocionalDB.isEmpty())
            return Left(Notification.create(new Error("PrecoPromocional not found: " + (aPrecoPromocionalCommand.aId() != null ?
                    aPrecoPromocionalCommand.aId() : aPrecoPromocionalCommand.aUuid()))));

        var notification = Notification.create();
        var precoPromocional = PrecoPromocional.update(aPrecoPromocionalDB.get().getId().getValue(),
                                                       aPrecoPromocionalDB.get().getUuid().getValue(),
                                                       aPrecoPromocionalCommand.aStatusCode(),
                                                       aPrecoPromocionalCommand.aProdutoId(),
                                                       aPrecoPromocionalCommand.aPrecoPromocional(),
                                                       aPrecoPromocionalCommand.aDataInicio(),
                                                       aPrecoPromocionalCommand.aDataFim());
        precoPromocional.validate(notification);

        return notification.hasError() ? Left(notification) : update(precoPromocional);
    }

    @Transactional
    private Either<Notification, UpdatePrecoPromocionalOutput> update(PrecoPromocional aPrecoPromocional) {

        return Try(() -> gateway.update(aPrecoPromocional))
                .toEither().bimap(Notification::create, UpdatePrecoPromocionalOutput::from);
    }
}
