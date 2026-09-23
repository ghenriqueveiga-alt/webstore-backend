package com.hvs.ws.back.app.usecase.arquivo;

import com.hvs.ws.back.app.command.arquivo.ReadAllArquivoCommand;
import com.hvs.ws.back.app.output.arquivo.ReadAllArquivoOutput;
import com.hvs.ws.back.domain.entity.arquivo.Arquivo;
import com.hvs.ws.back.domain.entity.arquivo.ArquivoDomainGateway;
import com.hvs.ws.back.domain.pagination.Pagination;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;

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
                .stream().filter(arquivo -> arquivo.getStatus().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {

            return Either.right(ReadAllArquivoOutput.from(Pagination.from(
                    arquivoPagination.aPageNumber(),
                    arquivoPagination.aTotalElements(),
                    arquivoPagination.aTotalPages(),
                    lista)));
        } else {

            return Either.left(Notification
                    .create(new Error("No Archive was found.")));
        }
    }
}
