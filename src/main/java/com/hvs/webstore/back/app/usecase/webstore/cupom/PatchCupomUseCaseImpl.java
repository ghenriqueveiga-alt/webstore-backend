package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.PatchCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.PatchCupomOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomId;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchCupomUseCaseImpl extends PatchCupomUseCase {

    private final CupomDomainGateway gateway;

    public PatchCupomUseCaseImpl(CupomDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchCupomOutput> execute(PatchCupomCommand aCupomCommand) {

        Optional<Cupom> aCupomDB = aCupomCommand.aId() != null ?
                gateway.read(CupomId.from(aCupomCommand.aId())) : gateway.readByUuid(CupomUuid.from(aCupomCommand.aUuid()));

        if (aCupomDB.isEmpty())
            return Either.left(Notification.create(new Error("Cupom not found: " + (aCupomCommand.aId() != null ?
                    aCupomCommand.aId() : aCupomCommand.aUuid()))));

        var notification = Notification.create();
        var cupom = Cupom.patch(aCupomCommand.aStatusCode(),
                                aCupomCommand.codigo(),
                                aCupomCommand.tipoDescontoCode(),
                                aCupomCommand.valorDesconto(),
                                aCupomCommand.valorMinimo(),
                                aCupomCommand.quantidadeMaxima(),
                                aCupomCommand.usosAtuais(),
                                aCupomCommand.dataExpiracao(),
                                aCupomCommand.ativo(),
                                aCupomDB.get());
        cupom.validate(notification);

        return notification.hasError() ? Left(notification) : patch(cupom);
    }

    @Transactional
    private Either<Notification, PatchCupomOutput> patch(Cupom aCupom) {

        return Try(() -> gateway.patch(aCupom)).toEither().bimap(Notification::create, PatchCupomOutput::from);
    }
}
