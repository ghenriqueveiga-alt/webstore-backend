package com.hvs.ws.back.app.usecase.bloco;

import com.hvs.ws.back.app.command.bloco.ReadAllBlocoCommand;
import com.hvs.ws.back.app.output.bloco.ReadAllBlocoOutput;
import com.hvs.ws.back.domain.entity.bloco.Bloco;
import com.hvs.ws.back.domain.entity.bloco.BlocoDomainGateway;
import com.hvs.ws.back.domain.pagination.Pagination;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;

public class ReadAllBlocoUseCaseImpl extends ReadAllBlocoUseCase {

    private final BlocoDomainGateway gateway;

    public ReadAllBlocoUseCaseImpl(
            BlocoDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllBlocoOutput> execute(ReadAllBlocoCommand aIn) {

        Pagination<Bloco> blocoPagination = this.gateway.readAll(aIn.aBlocoSearchQuery());
        List<Bloco> lista = blocoPagination.aContent()
                .stream().filter(bloco -> bloco.getStatus().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {

            return Either.right(ReadAllBlocoOutput.from(Pagination.from(
                    blocoPagination.aPageNumber(),
                    blocoPagination.aTotalElements(),
                    blocoPagination.aTotalPages(),
                    lista)));
        } else {

            return Either.left(Notification
                    .create(new Error("No Block was found.")));
        }
    }
}
