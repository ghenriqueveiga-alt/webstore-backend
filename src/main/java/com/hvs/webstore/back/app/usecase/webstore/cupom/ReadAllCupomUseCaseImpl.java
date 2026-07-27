package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.ReadAllCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.ReadAllCupomOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllCupomUseCaseImpl extends ReadAllCupomUseCase {

    private final CupomDomainGateway gateway;

    public ReadAllCupomUseCaseImpl(CupomDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCupomOutput> execute(ReadAllCupomCommand aCupomCommand) {

        Pagination<Cupom> cupomPagination = gateway.readAll(aCupomCommand.aSearchQuery());
        List<Cupom> lista = cupomPagination.aContent()
                .stream().filter(corte -> corte.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {

            return Try(() -> gateway.readAll(aCupomCommand.aSearchQuery())).toEither().bimap(
                    Notification::create, ReadAllCupomOutput::from);
        } else {

            return Either.left(Notification.create(new Error("No Cupom was found.")));
        }
    }
}
