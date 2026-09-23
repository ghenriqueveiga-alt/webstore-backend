package com.hvs.ws.back.app.usecase.genero;

import com.hvs.ws.back.app.command.genero.ReadAllGeneroCommand;
import com.hvs.ws.back.app.output.genero.ReadAllGeneroOutput;
import com.hvs.ws.back.domain.entity.genero.Genero;
import com.hvs.ws.back.domain.entity.genero.GeneroDomainGateway;
import com.hvs.ws.back.domain.pagination.Pagination;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;

public class ReadAllGeneroUseCaseImpl extends ReadAllGeneroUseCase {

    private final GeneroDomainGateway gateway;

    public ReadAllGeneroUseCaseImpl(GeneroDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllGeneroOutput> execute(ReadAllGeneroCommand aGeneroCommand) {

        Pagination<Genero> generoPagination = this.gateway.readAll(aGeneroCommand.aGeneroSearchQuery());
        List<Genero> lista = generoPagination.aContent()
                .stream().filter(genero -> genero.getStatus().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {
            return Either.right(ReadAllGeneroOutput.from(Pagination.from(
                    generoPagination.aPageNumber(),
                    generoPagination.aTotalElements(),
                    generoPagination.aTotalPages(),
                    lista)));
        } else {
            return Either.left(Notification.create(new Error("No Genero was found.")));
        }
    }
}
