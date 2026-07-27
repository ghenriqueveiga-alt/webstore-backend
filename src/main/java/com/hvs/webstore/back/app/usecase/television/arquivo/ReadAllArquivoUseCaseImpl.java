package com.hvs.webstore.back.app.usecase.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.ReadAllArquivoCommand;
import com.hvs.webstore.back.app.output.television.arquivo.ReadAllArquivoOutput;
import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllArquivoUseCaseImpl extends ReadAllArquivoUseCase {

    private final ArquivoDomainGateway gateway;

    public ReadAllArquivoUseCaseImpl(
            ArquivoDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllArquivoOutput> execute(ReadAllArquivoCommand aIn) {

        Pagination<Arquivo> arquivoPagination = this.gateway.readAll(aIn.aArquivoSearchQuery());
        List<Arquivo> lista = arquivoPagination.aContent()
                .stream().filter(arquivo -> arquivo.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {

            return Try(() -> this.gateway.readAll(aIn.aArquivoSearchQuery()))
                    .toEither()
                    .bimap(Notification::create, ReadAllArquivoOutput::from);
        } else {

            return Either.left(Notification
                    .create(new Error("No Archive was found.")));
        }
    }
}
