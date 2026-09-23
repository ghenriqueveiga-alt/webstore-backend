package com.hvs.ws.back.app.usecase.arquivo;

import com.hvs.ws.back.app.command.arquivo.CreateArquivoCommand;
import com.hvs.ws.back.app.output.arquivo.CreateArquivoOutput;
import com.hvs.ws.back.domain.entity.arquivo.Arquivo;
import com.hvs.ws.back.domain.entity.arquivo.ArquivoDomainGateway;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateArquivoUseCaseImpl extends CreateArquivoUseCase {

    private final ArquivoDomainGateway gateway;

    public CreateArquivoUseCaseImpl(
            ArquivoDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateArquivoOutput> execute(CreateArquivoCommand aIn) {

        final var notification = Notification.create();
        final var arquivo = Arquivo.create(aIn.aNome(),
                                           aIn.aTipoCode(),
                                           aIn.aTamanho(),
                                           aIn.aCaminho());
        arquivo.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(arquivo);
    }

    @Transactional
    private Either<Notification, CreateArquivoOutput> create(final Arquivo aArquivo){

        return Try(() -> this.gateway.create(aArquivo))
                .toEither()
                .bimap(Notification::create, CreateArquivoOutput::from);
    }
}
