package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.UpdateCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.UpdateCupomOutput;
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

public class UpdateCupomUseCaseImpl extends UpdateCupomUseCase {

    private final CupomDomainGateway gateway;

    public UpdateCupomUseCaseImpl(CupomDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateCupomOutput> execute(UpdateCupomCommand aCupomCommand) {

        Optional<Cupom> aCupomDB = aCupomCommand.aId() != null ?
                gateway.read(CupomId.from(aCupomCommand.aId())) : gateway.readByUuid(CupomUuid.from(aCupomCommand.aUuid()));

        if (aCupomDB.isEmpty())
            return Either.left(Notification.create(new Error("Cupom not found: " + (aCupomCommand.aId() != null ?
                    aCupomCommand.aId() : aCupomCommand.aUuid()))));

        var notification = Notification.create();
        var cupom = Cupom.update(aCupomDB.get().getId().getValue(),
                                 aCupomDB.get().getUuid().getValue(),
                                 aCupomCommand.aStatusCode(),
                                 aCupomCommand.codigo(),
                                 aCupomCommand.tipoDescontoCode(),
                                 aCupomCommand.valorDesconto(),
                                 aCupomCommand.valorMinimo(),
                                 aCupomCommand.quantidadeMaxima(),
                                 aCupomCommand.usosAtuais(),
                                 aCupomCommand.dataExpiracao(),
                                 aCupomCommand.criadoEm(),
                                 aCupomCommand.ativo());
        cupom.validate(notification);

        return notification.hasError() ? Left(notification) : update(cupom);
    }

    @Transactional
    private Either<Notification, UpdateCupomOutput> update(Cupom aCupom) {

        return Try(() -> gateway.update(aCupom))
                .toEither().bimap(Notification::create, UpdateCupomOutput::from);
    }
}
