package com.hvs.webstore.back.app.usecase.webstore.frete;

import com.hvs.webstore.back.app.command.webstore.frete.CalcularFreteCommand;
import com.hvs.webstore.back.app.output.webstore.frete.CalcularFreteOutput;
import com.hvs.webstore.back.domain.entity.webstore.frete.Frete;
import com.hvs.webstore.back.domain.entity.webstore.frete.FreteDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.frete.TipoFrete;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CalcularFreteUseCaseImpl extends CalcularFreteUseCase {

    private final FreteDomainGateway gateway;

    public CalcularFreteUseCaseImpl(FreteDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CalcularFreteOutput> execute(CalcularFreteCommand aFreteCommand) {

        var notification = Notification.create();
        var frete = Frete.create(aFreteCommand.cepOrigem(),
                                 aFreteCommand.cepDestino(),
                                 TipoFrete.findByCode(aFreteCommand.tipoFrete()),
                                 aFreteCommand.peso(),
                                 aFreteCommand.comprimento(),
                                 aFreteCommand.largura(),
                                 aFreteCommand.altura());
        frete.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(frete);
    }

    @Transactional
    private Either<Notification, CalcularFreteOutput> create(Frete aFrete) {

        return Try(() -> gateway.create(aFrete))
                .toEither().bimap(Notification::create, CalcularFreteOutput::from);
    }
}
