package com.hvs.webstore.back.app.usecase.television.bloco;

import com.hvs.webstore.back.app.command.television.bloco.ReadAllBlocoCommand;
import com.hvs.webstore.back.app.output.television.bloco.ReadAllBlocoOutput;
import com.hvs.webstore.back.domain.entity.television.bloco.Bloco;
import com.hvs.webstore.back.domain.entity.television.bloco.BlocoDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

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
                .stream().filter(bloco -> bloco.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {

            return Try(() -> this.gateway.readAll(aIn.aBlocoSearchQuery()))
                    .toEither()
                    .bimap(Notification::create, ReadAllBlocoOutput::from);
        } else {

            return Either.left(Notification
                    .create(new Error("No Block was found.")));
        }
    }
}
