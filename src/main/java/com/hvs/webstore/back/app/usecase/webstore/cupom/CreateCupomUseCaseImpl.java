package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.CreateCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.CreateCupomOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateCupomUseCaseImpl extends CreateCupomUseCase {

    private final CupomDomainGateway gateway;

    public CreateCupomUseCaseImpl(CupomDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateCupomOutput> execute(CreateCupomCommand aCupomCommand) {

        var notification = Notification.create();
        var cupom = Cupom.create(aCupomCommand.codigo(),
                                 aCupomCommand.tipoDescontoCode(),
                                 aCupomCommand.valorDesconto(),
                                 aCupomCommand.valorMinimo(),
                                 aCupomCommand.quantidadeMaxima(),
                                 aCupomCommand.dataExpiracao());
        cupom.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(cupom);
    }

    @Transactional
    private Either<Notification, CreateCupomOutput> create(Cupom aCupom) {

        return Try(() -> gateway.create(aCupom))
                .toEither().bimap(Notification::create, CreateCupomOutput::from);
    }
}
