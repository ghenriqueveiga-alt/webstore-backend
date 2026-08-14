package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.app.command.television.corte.ReadAllCorteCommand;
import com.hvs.webstore.back.app.output.television.corte.ReadAllCorteOutput;
import com.hvs.webstore.back.domain.entity.television.corte.Corte;
import com.hvs.webstore.back.domain.entity.television.corte.CorteDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;

public class ReadAllCorteUseCaseImpl extends ReadAllCorteUseCase {

    private final CorteDomainGateway gateway;

    public ReadAllCorteUseCaseImpl(
            CorteDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCorteOutput> execute(ReadAllCorteCommand aIn) {

        Pagination<Corte> cortePagination = this.gateway.readAll(aIn.aCorteSearchQuery());
        List<Corte> lista = cortePagination.aContent()
                .stream().filter(corte -> corte.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {

            return Either.right(ReadAllCorteOutput.from(Pagination.from(
                    cortePagination.aPageNumber(),
                    cortePagination.aTotalElements(),
                    cortePagination.aTotalPages(),
                    lista)));
        } else {

            return Either.left(Notification
                    .create(new Error("No Cut was found.")));
        }
    }
}
