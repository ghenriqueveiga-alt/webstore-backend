package com.hvs.webstore.back.app.usecase.webstore.frete;

import com.hvs.webstore.back.app.command.webstore.frete.ReadAllFreteCommand;
import com.hvs.webstore.back.app.output.webstore.frete.ReadAllFreteOutput;
import com.hvs.webstore.back.domain.entity.webstore.frete.Frete;
import com.hvs.webstore.back.domain.entity.webstore.frete.FreteDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllFreteUseCaseImpl extends ReadAllFreteUseCase {

    private final FreteDomainGateway gateway;

    public ReadAllFreteUseCaseImpl(FreteDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllFreteOutput> execute(ReadAllFreteCommand aFreteCommand) {

        Pagination<Frete> fretePagination = gateway.readAll(aFreteCommand.aSearchQuery());
        List<Frete> lista = fretePagination.aContent()
                .stream().filter(corte -> corte.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty())
            return Try(() -> gateway.readAll(aFreteCommand.aSearchQuery()))
                    .toEither().bimap(Notification::create, ReadAllFreteOutput::from);

        return Either.left(Notification.create(new Error("No Frete was found.")));
    }
}
