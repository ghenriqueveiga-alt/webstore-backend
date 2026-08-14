package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.app.command.television.genero.ReadAllGeneroCommand;
import com.hvs.webstore.back.app.output.television.genero.ReadAllGeneroOutput;
import com.hvs.webstore.back.domain.entity.television.genero.Genero;
import com.hvs.webstore.back.domain.entity.television.genero.GeneroDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
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
                .stream().filter(genero -> genero.getStatusCode().getDesc().equals("Active")).toList();

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
