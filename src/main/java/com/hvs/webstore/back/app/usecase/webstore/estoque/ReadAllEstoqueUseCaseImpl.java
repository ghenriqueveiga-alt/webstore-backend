package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.ReadAllEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.ReadAllEstoqueOutput;
import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllEstoqueUseCaseImpl extends ReadAllEstoqueUseCase {

    private final EstoqueDomainGateway gateway;

    public ReadAllEstoqueUseCaseImpl(EstoqueDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllEstoqueOutput> execute(ReadAllEstoqueCommand aEstoqueCommand) {

        Pagination<Estoque> estoquePagination = gateway.readAll(aEstoqueCommand.aSearchQuery());
        List<Estoque> lista = estoquePagination.aContent()
                .stream().filter(corte -> corte.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {
            return Try(() -> gateway.readAll(aEstoqueCommand.aSearchQuery())).toEither().bimap(
                    Notification::create, ReadAllEstoqueOutput::from);
        } else {
            return Either.left(Notification.create(new Error("No Estoque was found.")));
        }
    }
}
