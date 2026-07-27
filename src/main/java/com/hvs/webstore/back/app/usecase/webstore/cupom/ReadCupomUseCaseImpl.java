package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.ReadCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.CupomOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomId;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadCupomUseCaseImpl extends ReadCupomUseCase {

    private final CupomDomainGateway gateway;

    public ReadCupomUseCaseImpl(CupomDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CupomOutput> execute(ReadCupomCommand aCupomCommand) {

        Optional<Cupom> aCupomDB = aCupomCommand.aId() != null ?
                gateway.read(CupomId.from(aCupomCommand.aId())) : gateway.readByUuid(CupomUuid.from(aCupomCommand.aUuid()));

        if (aCupomDB.isPresent())
            return Try(aCupomDB::get).toEither().bimap(Notification::create, CupomOutput::from);

        var aCupomId = aCupomCommand.aId() != null ? String.valueOf(aCupomCommand.aId()) : aCupomCommand.aUuid();

        return Either.left(Notification.create(new Error("Cupom not found: " + aCupomId)));
    }
}
