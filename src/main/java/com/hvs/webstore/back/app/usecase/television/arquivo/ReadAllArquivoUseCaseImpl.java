package com.hvs.webstore.back.app.usecase.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.ReadAllArquivoCommand;
import com.hvs.webstore.back.app.output.television.arquivo.ReadAllArquivoOutput;
import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
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
