package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.ReadPrecoPromocionalCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.ReadPrecoPromocionalOutput;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalId;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadPrecoPromocionalUseCaseImpl extends ReadPrecoPromocionalUseCase {

    private final PrecoPromocionalDomainGateway gateway;

    public ReadPrecoPromocionalUseCaseImpl(PrecoPromocionalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadPrecoPromocionalOutput> execute(ReadPrecoPromocionalCommand aPrecoPromocionalCommand) {

        Optional<PrecoPromocional> aPrecoPromocionalDB = aPrecoPromocionalCommand.aId() != null ?
                gateway.read(PrecoPromocionalId.from(aPrecoPromocionalCommand.aId())) : gateway.readByUuid(PrecoPromocionalUuid.from(aPrecoPromocionalCommand.aUuid()));

        if (aPrecoPromocionalDB.isPresent())
            return Try(aPrecoPromocionalDB::get).toEither().bimap(Notification::create, ReadPrecoPromocionalOutput::from);

        var aPrecoPromocionalId = aPrecoPromocionalCommand.aId() != null ? String.valueOf(aPrecoPromocionalCommand.aId()) : aPrecoPromocionalCommand.aUuid();

        return Either.left(Notification.create(new Error("PrecoPromocional not found: " + aPrecoPromocionalId)));
    }
}
