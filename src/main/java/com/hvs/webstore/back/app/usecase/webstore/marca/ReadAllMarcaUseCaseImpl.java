package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.app.command.webstore.marca.ReadAllMarcaCommand;
import com.hvs.webstore.back.app.output.webstore.marca.ReadAllMarcaOutput;
import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllMarcaUseCaseImpl extends ReadAllMarcaUseCase {

    private final MarcaDomainGateway gateway;

    public ReadAllMarcaUseCaseImpl(MarcaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllMarcaOutput> execute(ReadAllMarcaCommand aMarcaCommand) {

        Pagination<Marca> marcaPagination = gateway.readAll(aMarcaCommand.aSearchQuery());
        List<Marca> lista = marcaPagination.aContent()
                .stream().filter(corte -> corte.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty())
            return Try(() -> gateway.readAll(aMarcaCommand.aSearchQuery()))
                    .toEither().bimap(Notification::create, ReadAllMarcaOutput::from);

        return Either.left(Notification.create(new Error("No Marca was found.")));
    }
}
