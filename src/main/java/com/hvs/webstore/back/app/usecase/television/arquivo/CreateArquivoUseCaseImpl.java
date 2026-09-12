package com.hvs.webstore.back.app.usecase.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.CreateArquivoCommand;
import com.hvs.webstore.back.app.output.television.arquivo.CreateArquivoOutput;
import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateArquivoUseCaseImpl extends CreateArquivoUseCase {

    private final ArquivoDomainGateway gateway;
    private final MediaPathResolver mediaPathResolver;

    public CreateArquivoUseCaseImpl(
            ArquivoDomainGateway gateway,
            MediaPathResolver mediaPathResolver) {
        this.gateway = gateway;
        this.mediaPathResolver = mediaPathResolver;
    }

    @Override
    public Either<Notification, CreateArquivoOutput> execute(CreateArquivoCommand aIn) {

        final var notification = Notification.create();
        final var arquivo = Arquivo.create(aIn.aNome(),
                                           aIn.aTipoCode(),
                                           aIn.aTamanho(),
                                           this.mediaPathResolver.relativize(aIn.aCaminho()));
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
