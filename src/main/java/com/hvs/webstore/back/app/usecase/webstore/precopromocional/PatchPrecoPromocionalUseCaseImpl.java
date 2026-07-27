package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.PatchPrecoPromocionalCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.PatchPrecoPromocionalOutput;
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

public class PatchPrecoPromocionalUseCaseImpl extends PatchPrecoPromocionalUseCase {

    private final PrecoPromocionalDomainGateway gateway;

    public PatchPrecoPromocionalUseCaseImpl(PrecoPromocionalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchPrecoPromocionalOutput> execute(PatchPrecoPromocionalCommand aPrecoPromocionalCommand) {

        Optional<PrecoPromocional> aPrecoPromocionalDB = aPrecoPromocionalCommand.aId() != null ?
                gateway.read(PrecoPromocionalId.from(aPrecoPromocionalCommand.aId())) : gateway.readByUuid(PrecoPromocionalUuid.from(aPrecoPromocionalCommand.aUuid()));

        if (aPrecoPromocionalDB.isEmpty())
            return Left(Notification.create(new Error("PrecoPromocional not found: " + (aPrecoPromocionalCommand.aId() != null ?
                    aPrecoPromocionalCommand.aId() : aPrecoPromocionalCommand.aUuid()))));

        var notification = Notification.create();
        var precoPromocional = PrecoPromocional.patch(aPrecoPromocionalCommand.aStatusCode(),
                                                       aPrecoPromocionalCommand.aProdutoId(),
                                                       aPrecoPromocionalCommand.aPrecoPromocional(),
                                                       aPrecoPromocionalCommand.aDataInicio(),
                                                       aPrecoPromocionalCommand.aDataFim(),
                                                       aPrecoPromocionalDB.get());
        precoPromocional.validate(notification);

        return notification.hasError() ? Left(notification) : patch(precoPromocional);
    }
    @Transactional
    private Either<Notification, PatchPrecoPromocionalOutput> patch(PrecoPromocional aPrecoPromocional) {

        return Try(() -> gateway.patch(aPrecoPromocional))
                .toEither().bimap(Notification::create, PatchPrecoPromocionalOutput::from);
    }
}
