package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.app.command.webstore.endereco.ReadAllEnderecoCommand;
import com.hvs.webstore.back.app.output.webstore.endereco.ReadAllEnderecoOutput;
import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllEnderecoUseCaseImpl extends ReadAllEnderecoUseCase {

    private final EnderecoDomainGateway gateway;

    public ReadAllEnderecoUseCaseImpl(EnderecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllEnderecoOutput> execute(ReadAllEnderecoCommand aEnderecoCommand) {

        Pagination<Endereco> enderecoPagination = gateway.readAll(aEnderecoCommand.aSearchQuery());
        List<Endereco> lista = enderecoPagination.aContent()
                .stream().filter(endereco -> endereco.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {
            return Try(() -> gateway.readAll(aEnderecoCommand.aSearchQuery()))
                    .toEither().bimap(Notification::create, ReadAllEnderecoOutput::from);
        } else {
            return Either.left(Notification.create(new Error("No Endereco was found.")));
        }
    }
}
